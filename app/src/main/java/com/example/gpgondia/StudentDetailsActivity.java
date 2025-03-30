package com.example.gpgondia;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class StudentDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail); // Ensure you have this layout

        // Find the back button
        TextView backButton = findViewById(R.id.back_button);
        ImageView backButton2 = findViewById(R.id.back_button2);


        // Set click listener to navigate back
        backButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Go back to the previous activity
            }
        });
        backButton2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Go back to the previous activity
            }
        });

    }
}
