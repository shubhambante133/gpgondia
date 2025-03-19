package com.example.gpgondia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class StudentDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);  // Ensure this XML exists

        // Reference TextViews
        TextView studentName = findViewById(R.id.student_name);
        TextView studentEnrollment = findViewById(R.id.student_Enrollment_no);
        TextView studentBranch = findViewById(R.id.student_branch);
        TextView studentEmail = findViewById(R.id.student_email);
        TextView studentMobile = findViewById(R.id.student_mobile);

        // Get Data from Intent
        String name = getIntent().getStringExtra("name");
        String enrollment = getIntent().getStringExtra("Enrollment");
        String branch = getIntent().getStringExtra("Branch");
        String email = getIntent().getStringExtra("Email");
        String mobile = getIntent().getStringExtra("Mobile");  // Integer hata ke String use karo

        // Set Data
        studentName.setText(" " + name);
        studentEnrollment.setText("Enrollment: " + enrollment);
        studentBranch.setText("Branch: " + branch);
        studentEmail.setText("Email: " + email);
        studentMobile.setText("Mobile: " + mobile);
    }
}