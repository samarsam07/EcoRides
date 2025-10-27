package com.samar.ecoRides.controller;

import com.samar.ecoRides.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class ChatController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat.sendMessage/{rideId}")
    public void sendMessage(@DestinationVariable Long rideId, @Payload ChatMessage chatMessage){
        chatMessage.setTimestamp(LocalDateTime.now());
        chatMessage.setRideId(rideId);

        simpMessagingTemplate.convertAndSend(String.format("/topic/ride/%d/chat", rideId), chatMessage);
    }

    @MessageMapping("chat.addUser/{rideId}")
    public void addUser(@DestinationVariable Long rideId, @Payload ChatMessage chatMessage,
                        SimpMessageHeaderAccessor headerAccessor){
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            chatMessage.setSenderUsername(username);
            chatMessage.setType(ChatMessage.MessageType.JOIN);
            chatMessage.setContent(username + " joined the chat!");
            chatMessage.setTimestamp(LocalDateTime.now());
            chatMessage.setRideId(rideId);

            simpMessagingTemplate.convertAndSend(String.format("/topic/ride/%d/chat", rideId), chatMessage);
        } else {
            System.err.println("Username not found in WebSocket session for addUser");
        }
    }

}
