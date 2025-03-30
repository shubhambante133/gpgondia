package com.example.gpgondia;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Registerform extends AppCompatActivity {

    private EditText name, enrollment,scheme, dob, mobile, email, password, repassword;
    private Spinner branch, year;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerform);

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
        String[] branches = {"Information Technology","Computer Engineering", "Electronics & Telecommunicatin","Electrical Engineering", "Mechanical Engineering", "Civil Engineering"};
        ArrayAdapter<String> branchAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, branches);
        branch.setAdapter(branchAdapter);

        // Set up dropdown options for Year
        String[] years = {"1st Year", "2nd Year", "3rd Year"};
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, years);
        year.setAdapter(yearAdapter);

        // Handle registration button click
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredName = name.getText().toString();
                String enteredEnrollment = enrollment.getText().toString();
                String enteredScheme = scheme.getText().toString();
                String enteredDOB = dob.getText().toString();
                String enteredMobile = mobile.getText().toString();
                String enteredEmail = email.getText().toString();
                String enteredPassword = password.getText().toString();
                String enteredRePassword = repassword.getText().toString();

                if (enteredName.isEmpty() || enteredEnrollment.isEmpty() || enteredDOB.isEmpty() ||
                        enteredMobile.isEmpty() || enteredEmail.isEmpty() || enteredPassword.isEmpty() ||
                        enteredRePassword.isEmpty()) {
                    Toast.makeText(Registerform.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else if (!enteredPassword.equals(enteredRePassword)) {
                    Toast.makeText(Registerform.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Registerform.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
