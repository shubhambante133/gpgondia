package com.example.gpgondia;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registerform extends AppCompatActivity {

    private EditText name, enrollment, scheme, dob, mobile, email, password, repassword;
    private Spinner branch, year;
    private Button registerButton;

    // Firebase references
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerform);

        // Initialize UI elements
        name = findViewById(R.id.name);
        enrollment = findViewById(R.id.enrollment);
        scheme = findViewById(R.id.scheme);
        dob = findViewById(R.id.dob);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        branch = findViewById(R.id.branch);
        year = findViewById(R.id.year);
        registerButton = findViewById(R.id.registerButton);

        // Set up dropdown options for Branch
        String[] branches = {"Information Technology", "Computer Engineering", "Electronics & Telecommunication",
                "Electrical Engineering", "Mechanical Engineering", "Civil Engineering"};
        ArrayAdapter<String> branchAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, branches);
        branch.setAdapter(branchAdapter);

        // Set up dropdown options for Year
        String[] years = {"1st Year", "2nd Year", "3rd Year"};
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, years);
        year.setAdapter(yearAdapter);

        // Initialize Firebase references
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Students");

        // Handle registration button click
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerStudent();
            }
        });
    }

    // Method to handle student registration
    private void registerStudent() {
        String enteredName = name.getText().toString().trim();
        String enteredEnrollment = enrollment.getText().toString().trim();
        String enteredScheme = scheme.getText().toString().trim();
        String enteredDOB = dob.getText().toString().trim();
        String enteredMobile = mobile.getText().toString().trim();
        String enteredEmail = email.getText().toString().trim();
        String enteredPassword = password.getText().toString().trim();
        String enteredRePassword = repassword.getText().toString().trim();
        String selectedBranch = branch.getSelectedItem().toString();
        String selectedYear = year.getSelectedItem().toString();

        // Validate inputs
        if (enteredName.isEmpty() || enteredEnrollment.isEmpty() || enteredScheme.isEmpty() ||
                enteredDOB.isEmpty() || enteredMobile.isEmpty() || enteredEmail.isEmpty() ||
                enteredPassword.isEmpty() || enteredRePassword.isEmpty()) {
            Toast.makeText(Registerform.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if passwords match
        if (!enteredPassword.equals(enteredRePassword)) {
            Toast.makeText(Registerform.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Register user with Firebase Authentication
        auth.createUserWithEmailAndPassword(enteredEmail, enteredPassword)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Get current user after registration
                        FirebaseUser user = auth.getCurrentUser();
                        if (user != null) {
                            // Create Student object
                            Student student = new Student(enteredName, enteredEnrollment, enteredScheme, enteredDOB,
                                    enteredMobile, enteredEmail, selectedBranch, selectedYear,enteredPassword);

                            // Store data in Firebase Realtime Database using enrollment number as key
                            databaseReference.child(enteredEnrollment).setValue(student)
                                    .addOnSuccessListener(aVoid -> {
                                        Toast.makeText(Registerform.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                                        clearFields();
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(Registerform.this, "Failed to store data! Try again.", Toast.LENGTH_SHORT).show();
                                    });
                        }
                    } else {
                        Toast.makeText(Registerform.this, "Authentication Failed! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });

    }
    // Method to clear all input fields after successful registration
    private void clearFields() {
        name.setText("");
        enrollment.setText("");
        scheme.setText("");
        dob.setText("");
        mobile.setText("");
        email.setText("");
        password.setText("");
        repassword.setText("");
        branch.setSelection(0); // Reset spinner to first item
        year.setSelection(0);
    }

}
