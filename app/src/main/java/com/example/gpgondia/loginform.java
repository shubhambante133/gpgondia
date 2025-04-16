package com.example.gpgondia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class loginform extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private TextView registerTextView;

    private DatabaseReference studentsRef;
    private DatabaseReference adminsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginform);

        // Initialize Firebase Database references
        studentsRef = FirebaseDatabase.getInstance().getReference("Students");
        adminsRef = FirebaseDatabase.getInstance().getReference("Admin");

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerTextView = findViewById(R.id.registerTextView);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(loginform.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    authenticateUser(username, password);
                }
            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginform.this, Registerform.class);
                startActivity(intent);
            }
        });
    }

    private void authenticateUser(String username, String password) {
        // First, check if user is an admin
        adminsRef.child(username).get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult().exists()) {
                String storedPassword = task.getResult().child("password").getValue(String.class);
                String name = task.getResult().child("username").getValue(String.class);

                if (storedPassword != null && storedPassword.equals(password)) {
                    Toast.makeText(loginform.this, "Welcome Admin " + name, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(loginform.this, admin.class));
                } else {
                    Toast.makeText(loginform.this, "Incorrect Admin Password", Toast.LENGTH_SHORT).show();
                }
            } else {
                // If not an admin, check students
                authenticateStudent(username, password);
            }
        });
    }

    private void authenticateStudent(String username, String password) {
        studentsRef.child(username).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (task.getResult().exists()) {
                    DataSnapshot dataSnapshot = task.getResult();
                    String storedPassword = dataSnapshot.child("password").getValue(String.class);
                    String name = dataSnapshot.child("name").getValue(String.class);

                    if (storedPassword != null && storedPassword.equals(password)) {
                        Toast.makeText(loginform.this, "Welcome " + name, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(loginform.this, MainActivity.class);
                        intent.putExtra("Enrollment", username);
                        startActivity(intent);
                    } else {
                        Toast.makeText(loginform.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(loginform.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(loginform.this, "Error accessing database", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
