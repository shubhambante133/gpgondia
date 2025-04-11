package com.example.gpgondia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private ViewFlipper viewFlipper;
    private TextView userName;
    private ImageView profileImage, noticeIcon, descriptionIcon;

    // Firebase Database Reference
    private DatabaseReference databaseReference;

    private String enrollmentNumber; // Dynamically received from loginform

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        profileImage = findViewById(R.id.profile_image);
        userName = findViewById(R.id.user_name);
        viewFlipper = findViewById(R.id.view_flipper);
        TextView marqueeText = findViewById(R.id.marquee_text); // Scrolling text
        noticeIcon = findViewById(R.id.notice); // Notice icon
        descriptionIcon = findViewById(R.id.discription);

        // Set default profile image
        profileImage.setImageResource(R.drawable.student_profile);

        // ViewFlipper configuration
        viewFlipper.setFlipInterval(5000);
        viewFlipper.startFlipping();
        marqueeText.setSelected(true);

        // Get enrollment number from Intent after login
        enrollmentNumber = getIntent().getStringExtra("Enrollment");

        if (enrollmentNumber != null) {
            // Initialize Firebase Database Reference
            databaseReference = FirebaseDatabase.getInstance().getReference("Students").child(enrollmentNumber);

            // Fetch student name from Firebase dynamically
            fetchStudentName(enrollmentNumber);
        } else {
            Toast.makeText(MainActivity.this, "Error fetching student data", Toast.LENGTH_SHORT).show();
        }

        // Open StudentDetailsActivity when profile image is clicked
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StudentDetailsActivity.class);
                intent.putExtra("Enrollment", enrollmentNumber);
                startActivity(intent);
            }
        });
        // Open NoticeActivity
        noticeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoticeActivity.class);
                startActivity(intent);
            }
        });

        // Open Description/Info Activity (if needed)
        descriptionIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add functionality if required
                Toast.makeText(MainActivity.this, "Description clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchStudentName(String enrollment) {
        databaseReference.child("name").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (task.getResult().exists()) {
                    String name = task.getResult().getValue(String.class);
                    userName.setText("Welcome " + name); // Set fetched name
                } else {
                    userName.setText("Student Name Not Found");
                }
            } else {
                Toast.makeText(MainActivity.this, "Error fetching student name", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
