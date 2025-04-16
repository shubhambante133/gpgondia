package com.example.gpgondia;

public class ChatMessage {
    private String message;
    private String imageUrl;
    private long timestamp;
    private String sender;
    private boolean isImage; // ✅ This was missing

    public ChatMessage() {
        // Required for Firestore
    }

    // Constructor for text messages
    public ChatMessage(String message, String sender) {
        this.message = message;
        this.sender = sender;
        this.timestamp = System.currentTimeMillis();
        this.isImage = false;
    }

    // Constructor for image messages
    public ChatMessage(String imageUrl, boolean isImage, String sender) {
        this.imageUrl = imageUrl;
        this.isImage = isImage;
        this.sender = sender;
        this.timestamp = System.currentTimeMillis();
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public boolean isImage() {
        return isImage;
    }

    public void setImage(boolean image) {
        isImage = image;
    }
}