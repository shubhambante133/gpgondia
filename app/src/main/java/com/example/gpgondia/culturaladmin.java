package com.example.gpgondia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class culturaladmin extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private RecyclerView recyclerView;
    private EditText editTextMessage;
    private Button buttonSend, buttonAttach;
    private List<ChatMessage> chatMessages;
    private ChatAdapter chatAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sportactivityadmin);

        // Initialize UI components
        recyclerView = findViewById(R.id.recyclerView);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);
        buttonAttach = findViewById(R.id.buttonAttach);

        // Initialize RecyclerView
        chatMessages = new ArrayList<>();
        chatAdapter = new ChatAdapter(this, chatMessages);
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
            chatMessages.add(new ChatMessage(message)); // Use correct constructor
            chatAdapter.notifyDataSetChanged();
            editTextMessage.setText("");  // Clear input field
            recyclerView.scrollToPosition(chatMessages.size() - 1);
        }
    }


    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select an image"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            if (imageUri != null) {
                chatMessages.add(new ChatMessage(imageUri));

                chatAdapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(chatMessages.size() - 1);
            }
        }
    }
}
