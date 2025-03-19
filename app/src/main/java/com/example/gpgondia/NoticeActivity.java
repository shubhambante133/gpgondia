package com.example.gpgondia;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class NoticeActivity extends AppCompatActivity {

    ImageView btnClose;  // Change Button to ImageView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        btnClose = findViewById(R.id.back_button);  // No error now!

        // Close button event
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Closes the activity
            }
        });
    }
}