package com.samar.ecoRides.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private String content;
    private String senderUsername;
    private Long rideId;
    private MessageType type;
    private LocalDateTime timestamp;
    
    

    public ChatMessage() {
	}

	public ChatMessage(String content, String senderUsername, Long rideId, MessageType type, LocalDateTime timestamp) {
		super();
		this.content = content;
		this.senderUsername = senderUsername;
		this.rideId = rideId;
		this.type = type;
		this.timestamp = timestamp;
	}

	public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSenderUsername() {
		return senderUsername;
	}

	public void setSenderUsername(String senderUsername) {
		this.senderUsername = senderUsername;
	}

	public Long getRideId() {
		return rideId;
	}

	public void setRideId(Long rideId) {
		this.rideId = rideId;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
    
}