package com.example.gpgondia;

import android.net.Uri;

public class ChatMessage {
    private String message;
    private Uri imageUri;

    // Constructor for text messages
    public ChatMessage(String message) {
        this.message = message;
        this.imageUri = null;
    }

    // Constructor for image messages
    public ChatMessage(Uri imageUri) {
        this.message = null;
        this.imageUri = imageUri;
    }

    public String getMessage() {
        return message;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public boolean hasImage() {
        return imageUri != null;
    }

    public boolean hasText() {
        return message != null && !message.isEmpty();
    }
}
