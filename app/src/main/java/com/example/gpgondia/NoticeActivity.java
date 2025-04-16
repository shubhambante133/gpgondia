package com.example.gpgondia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class NoticeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        findViewById(R.id.sport).setOnClickListener(view ->
                startActivity(new Intent(NoticeActivity.this, sportstudent.class))
        );
        findViewById(R.id.placement).setOnClickListener(view ->
                startActivity(new Intent(NoticeActivity.this, placementstudent.class))
        );
        findViewById(R.id.conference).setOnClickListener(view ->
                startActivity(new Intent(NoticeActivity.this, conferencestudent.class))
        );
        findViewById(R.id.cultural).setOnClickListener(view ->
                startActivity(new Intent(NoticeActivity.this, culturalstudent.class))
        );
    }
}
