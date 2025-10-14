package com.samar.ecoRides.controller;

import com.razorpay.RazorpayException;
import com.samar.ecoRides.dto.PaymentDto;
import com.samar.ecoRides.dto.PayoutDto;
import com.samar.ecoRides.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/ride/{id}")
    public ResponseEntity<PaymentDto> createPayment(@PathVariable Long id) throws RazorpayException {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        PaymentDto payment=paymentService.createPaymentOrder(id,username);
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody Map<String,Object> payload){
        String orderId=(String) payload.get("order_id");
        String paymentId=(String) payload.get("payment_id");
        String signature=(String) payload.get("signature");

        paymentService.handlePaymentSuccess(orderId,paymentId,signature);
        return ResponseEntity.ok("Payment verified");
    }

    @PostMapping("/release/{rideId}")
    public ResponseEntity<PayoutDto> releasePayment(@PathVariable Long rideId){
        PayoutDto payoutDto=paymentService.releasePayment(rideId);
        return ResponseEntity.ok(payoutDto);

    }
}
