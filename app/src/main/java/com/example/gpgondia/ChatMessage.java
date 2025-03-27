package com.example.gpgondia;

public class ChatMessage {
    private String message;
    private boolean isUser; // true for user, false for bot

    public ChatMessage(String message, boolean isUser) {
        this.message = message;
        this.isUser = isUser;
    }

    public String getMessage() {
        return message;
    }

    public boolean isUser() {
        return isUser;
    }
}