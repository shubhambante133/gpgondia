package com.example.gpgondia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class conferenceadmin extends AppCompatActivity {

    private static final int PICK_FILE_REQUEST = 1;
    private RecyclerView recyclerView;
    private EditText editTextMessage;
    private Button buttonSend, buttonAttach;
    private List<ChatMessage> chatMessages;
    private ChatAdapter chatAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conferenceadmin); // Ensure correct layout

        // Initialize UI components
        recyclerView = findViewById(R.id.recyclerView);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);
        buttonAttach = findViewById(R.id.buttonAttach); // Initialize the attach button

        // Initialize RecyclerView
        chatMessages = new ArrayList<>();
        chatAdapter = new ChatAdapter(chatMessages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(chatAdapter);

        // Send button click listener
        buttonSend.setOnClickListener(v -> sendMessage());

        // Attach button click listener
        buttonAttach.setOnClickListener(v -> openFileChooser());
    }

    private void sendMessage() {
        String message = editTextMessage.getText().toString().trim();
        if (!message.isEmpty()) {
            chatMessages.add(new ChatMessage("You: " + message, true));
            chatMessages.add(new ChatMessage("Bot: " + generateBotResponse(message), false));
            chatAdapter.notifyDataSetChanged();
            editTextMessage.setText("");
            recyclerView.scrollToPosition(chatMessages.size() - 1);
        }
    }

    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*"); // Allow any file type
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select a file"), PICK_FILE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri fileUri = data.getData();
            if (fileUri != null) {
                chatMessages.add(new ChatMessage("You attached a file: " + fileUri.getLastPathSegment(), true));
                chatAdapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(chatMessages.size() - 1);
            }
        }
    }

    private String generateBotResponse(String userMessage) {
        return " " + userMessage;
    }
}
