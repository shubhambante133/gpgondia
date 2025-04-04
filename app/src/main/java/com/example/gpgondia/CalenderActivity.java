package com.example.gpgondia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalenderActivity extends AppCompatActivity {
    TextView ift,cot,ejt,eet,met,cet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        ift = findViewById(R.id.ift);
        cot = findViewById(R.id.cot);
        ejt = findViewById(R.id.ejt);
        eet = findViewById(R.id.eet);
        met = findViewById(R.id.met);
        cet = findViewById(R.id.cet);

        ift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalenderActivity.this, iftimetable.class);
                startActivity(intent);
            }
        });
        cot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalenderActivity.this, COtimetable.class);
                startActivity(intent);
            }
        });
        ejt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalenderActivity.this, EJtimetable.class);
                startActivity(intent);
            }
        });
        eet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalenderActivity.this, EEtimetable.class);
                startActivity(intent);
            }
        });
        met.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalenderActivity.this, MEtimetable.class);
                startActivity(intent);
            }
        });
        cet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalenderActivity.this, CEtimetable.class);
                startActivity(intent);
            }
        });
    }
}
