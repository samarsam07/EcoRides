package com.samar.ecoRides.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.samar.ecoRides.dao.RideDao;
import com.samar.ecoRides.dao.RideParticipantDao;
import com.samar.ecoRides.dto.PaymentDto;
import com.samar.ecoRides.dto.PayoutDto;
import com.samar.ecoRides.mapper.DtoMapper;
import com.samar.ecoRides.model.Ride;
import com.samar.ecoRides.model.RideParticipant;
import com.samar.ecoRides.model.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private RazorpayClient razorpayClient;

    @Autowired
    private RideDao rideDao;

    @Autowired
    private UserService userService;

    @Autowired
    private RideParticipantDao participantDao;

    private final DtoMapper DTOMAPPER = new DtoMapper();

    public PaymentDto createPaymentOrder(Long rideId, String username) throws RazorpayException {
        Ride ride = rideDao.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride Not Found"));

        User user = userService.findByUserName(username);
        RideParticipant rideParticipant = participantDao.findByRideAndUser(ride, user)
                .orElseThrow(() -> new RuntimeException("Participant Not Found"));

        int shareAmount = ride.getTotalCost() / ride.getCapacity();
        rideParticipant.setShareAmount(shareAmount);

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", shareAmount * 100);
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "ride_" + rideId + "_user_" + user.getUserId());

        Order order = razorpayClient.orders.create(orderRequest);
        rideParticipant.setOrderId(order.get("id"));
        rideParticipant.setPaymentStatus("PENDING");

        participantDao.save(rideParticipant);
        return DTOMAPPER.toPaymentDto(rideParticipant);
    }

    public void handlePaymentSuccess(String orderId, String paymentId, String signature) {
        RideParticipant participant = participantDao.findByOrderId(orderId);

        participant.setPaymentId(paymentId);
        participant.setPaymentStatus("PAID");
        participantDao.save(participant);

        Ride ride = participant.getRide();

        List<RideParticipant> participants = participantDao.findByRide(ride);

        int totalCollected = participants.stream()
                .filter(p -> "PAID".equals(p.getPaymentStatus()))
                .mapToInt(RideParticipant::getShareAmount)
                .sum();

        ride.setEscrowStatus("COLLECTING");
        rideDao.save(ride);
    }

    public PayoutDto releasePayment(Long rideId) {
        Ride ride = rideDao.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));


        if (!"COLLECTING".equals(ride.getEscrowStatus()))
            throw new RuntimeException("No funds to release yet");

        // Build payout JSON body
        JSONObject payoutRequest = new JSONObject();
        payoutRequest.put("account_number", System.getenv("RAZORPAY_ACCOUNT_NUMBER"));

        JSONObject fundAccount = new JSONObject();
        fundAccount.put("account_type", "vpa");

        JSONObject vpa = new JSONObject();
        vpa.put("address", ride.getOrganizer().getUpiId()); // organizer’s UPI ID from User entity

        JSONObject contact = new JSONObject();
        contact.put("name", ride.getOrganizer().getUserName());

        fundAccount.put("vpa", vpa);
        fundAccount.put("contact", contact);

        payoutRequest.put("fund_account", fundAccount);
        payoutRequest.put("amount", ride.getTotalCollected() * 100); // paise
        payoutRequest.put("currency", "INR");
        payoutRequest.put("mode", "UPI");
        payoutRequest.put("purpose", "payout");

        // Send the request to RazorpayX
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(
                System.getenv("RAZORPAY_KEY_ID"),
                System.getenv("RAZORPAY_KEY_SECRET")
        );

        HttpEntity<String> entity = new HttpEntity<>(payoutRequest.toString(), headers);
        String url = "https://api.razorpay.com/v1/payouts";

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            System.out.println("Payout response: " + response.getBody());

            // Update DB after payout success
            ride.setEscrowStatus("RELEASED");
            rideDao.save(ride);

            return DTOMAPPER.toPayoutDto(ride);
        } catch (Exception e) {
            throw new RuntimeException("Failed to release payout: " + e.getMessage(), e);
        }
    }
}
