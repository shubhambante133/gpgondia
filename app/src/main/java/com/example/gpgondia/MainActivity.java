package com.example.gpgondia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Views ko reference karo
        ImageView profileImage = findViewById(R.id.profile_image);
        TextView userName = findViewById(R.id.user_name);
        viewFlipper = findViewById(R.id.view_flipper);
        TextView marqueeText = findViewById(R.id.marquee_text); // Scrolling text
        ImageView noticeIcon = findViewById(R.id.notice); // Notice ka icon
        ImageView calendarIcon = findViewById(R.id.calendar_icon); // Calendar Icon
        ImageView discription = findViewById(R.id.discription);

        userName.setText("Shubham Kailash Bante");

        profileImage.setImageResource(R.drawable.student_profile);

        viewFlipper.setFlipInterval(1000);
        viewFlipper.startFlipping();
        marqueeText.setSelected(true);

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StudentDetailsActivity.class);

                // Student details pass karo
                intent.putExtra("name", "Shubham Kailash Bante");
                intent.putExtra("Enrollment", "2212420179");
                intent.putExtra("Branch", "Information Technology");
                intent.putExtra("Email", "admin123@gmail.com");
                intent.putExtra("Mobile", "9764766351");

                startActivity(intent);
            }
        });

        calendarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalenderActivity.class);
                startActivity(intent);
            }
        });

        noticeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoticeActivity.class);
                startActivity(intent);
            }
        });
    }

}

