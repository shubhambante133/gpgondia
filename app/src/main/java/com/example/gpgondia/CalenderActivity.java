package com.example.gpgondia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log; // Import Log for debugging
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast; // Import Toast for user feedback
import androidx.appcompat.app.AppCompatActivity;

public class CalenderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        // Find the back button
        TextView backButton = findViewById(R.id.back_button);
        if (backButton != null) {
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed(); // Go back to the previous activity
                }
            });
        }
        // Find the IF section and set click listener
        LinearLayout IFSection = findViewById(R.id.IF);
        if (IFSection != null) {
            IFSection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CalenderActivity.this, iftimetable.class);
                    startActivity(intent);
                }
            });
        }
        LinearLayout coSection = findViewById(R.id.CO);

        // Set the OnClickListener to open COtimetable when clicked
        coSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the COtimetable activity
                Intent intent = new Intent(CalenderActivity.this, COtimetable.class);
                startActivity(intent);
            }
        });

        LinearLayout ejSection = findViewById(R.id.EJ);

        // Set the OnClickListener to open COtimetable when clicked
        ejSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the EJtimetable activity
                Intent intent = new Intent(CalenderActivity.this, EJtimetable.class);
                startActivity(intent);
            }
        });

        LinearLayout eeSection = findViewById(R.id.EE);

        // Set the OnClickListener to open COtimetable when clicked
        eeSection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               // Start the EEtimetable activity
                Intent intent = new Intent(CalenderActivity.this, EEtimetable.class);
                startActivity(intent);
            }
        });
        LinearLayout meSection = findViewById(R.id.ME);

        // Set the OnClickListener to open COtimetable when clicked
        meSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the EJtimetable activity
                Intent intent = new Intent(CalenderActivity.this, MEtimetable.class);
                startActivity(intent);
            }
        });

        LinearLayout ceSection = findViewById(R.id.CE);

        // Set the OnClickListener to open COtimetable when clicked
        ceSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the EJtimetable activity
                Intent intent = new Intent(CalenderActivity.this, CEtimetable.class);
                startActivity(intent);
            }
        });
        }
    }

