package com.example.gpgondia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class loginform extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private TextView registerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginform);

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
                    // Perform login validation (this is a placeholder, replace with actual authentication logic)
                    if (username.equals("student") && password.equals("1234")) {
                        Toast.makeText(loginform.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        // Redirect to another activity after successful login
                        startActivity(new Intent(loginform.this, MainActivity.class));
                    } if (username.equals("admin") && password.equals("1234")){
                        Toast.makeText(loginform.this, "Admin Login Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(loginform.this, admin.class));
                    }
                }
            }
        });
        TextView registerformSection = findViewById(R.id.registerTextView);

        // Set the OnClickListener to open COtimetable when clicked
        registerformSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the EJtimetable activity
                Intent intent = new Intent(loginform.this, Registerform.class);
                startActivity(intent);
            }
        });
    }
}
