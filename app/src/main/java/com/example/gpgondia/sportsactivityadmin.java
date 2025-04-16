package com.example.gpgondia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class sportsactivityadmin extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private RecyclerView recyclerView;
    private EditText editTextMessage;
    private Button buttonSend, buttonAttach;
    private List<ChatMessage> chatMessages;
    private ChatAdapter chatAdapter;

    private FirebaseFirestore firestore;
    private StorageReference storageReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sportactivityadmin);

        // Initialize Firebase
        firestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference("chat_images");

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
            ChatMessage chatMessage = new ChatMessage(message, "Admin");

            // Save to Firestore
            firestore.collection("sports_chat").add(chatMessage);

            // Update UI
            chatMessages.add(chatMessage);
            chatAdapter.notifyDataSetChanged();
            editTextMessage.setText("");
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

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            Log.d("ImageSelected", "URI: " + imageUri.toString());

            StorageReference fileRef = storageReference.child(System.currentTimeMillis() + ".jpg");

            fileRef.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        Log.d("Upload", "Image uploaded successfully");

                        fileRef.getDownloadUrl()
                                .addOnSuccessListener(uri -> {
                                    String imageUrl = uri.toString();
                                    Log.d("DownloadURL", "URL: " + imageUrl);

                                    ChatMessage imageMessage = new ChatMessage(imageUrl, true, "Admin");

                                    firestore.collection("sports_chat").add(imageMessage)
                                            .addOnSuccessListener(documentReference -> {
                                                Log.d("Firestore", "Image message saved");
                                                chatMessages.add(imageMessage);
                                                chatAdapter.notifyDataSetChanged();
                                                recyclerView.scrollToPosition(chatMessages.size() - 1);
                                            })
                                            .addOnFailureListener(e -> {
                                                Log.e("Firestore", "Failed to save image message", e);
                                            });
                                })
                                .addOnFailureListener(e -> {
                                    Log.e("DownloadURL", "Failed to get image URL", e);
                                });
                    })
                    .addOnFailureListener(e -> {
                        Log.e("Upload", "Image upload failed", e);
                    });
        }
    }

}
