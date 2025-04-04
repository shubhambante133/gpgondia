package com.example.gpgondia;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentDetailsActivity extends AppCompatActivity {

    private TextView studentName, enrollmentNumber, branch, mobile,year,dob,email;
    private DatabaseReference databaseReference;
    private String enrollment;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        // Initialize views
        studentName = findViewById(R.id.student_name);
        enrollmentNumber = findViewById(R.id.enrollment);
        branch = findViewById(R.id.branch);
        mobile = findViewById(R.id.phoneno);
        email = findViewById(R.id.email);
        year = findViewById(R.id.year);
        dob = findViewById(R.id.DOB);

        // Get enrollment number from Intent
        enrollment = getIntent().getStringExtra("Enrollment");

        if (enrollment != null) {
            // Initialize Firebase Database Reference
            databaseReference = FirebaseDatabase.getInstance().getReference("Students").child(enrollment);

            // Fetch complete student data
            fetchStudentDetails(enrollment);
        } else {
            Toast.makeText(this, "Error fetching student data", Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchStudentDetails(String enrollment) {
        databaseReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (task.getResult().exists()) {
                    DataSnapshot dataSnapshot = task.getResult();

                    // Fetch data and add labels
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String enrollmentNum = dataSnapshot.child("enrollment").getValue(String.class);
                    String branchName = dataSnapshot.child("branch").getValue(String.class);
                    String mobileNum = dataSnapshot.child("mobile").getValue(String.class);
                    String Year = dataSnapshot.child("year").getValue(String.class);
                    String Dob = dataSnapshot.child("dob").getValue(String.class);
                    String Email = dataSnapshot.child("email").getValue(String.class);

                    // Display data with labels
                    studentName.setText("" + name);
                    enrollmentNumber.setText("Enrollment No       : " + enrollmentNum);
                    branch.setText("Branch                  : " + branchName);
                    mobile.setText("Mobile NO             : " + mobileNum);
                    year.setText("Year                      : "+Year);
                    dob.setText("DOB                     : "+Dob);
                    email.setText("Email                     : "+Email);

                } else {
                    Toast.makeText(this, "Student data not found", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Error fetching student details", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

