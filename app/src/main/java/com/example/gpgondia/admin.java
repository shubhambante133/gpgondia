package com.example.gpgondia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class admin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        findViewById(R.id.sport).setOnClickListener(view ->
                startActivity(new Intent(admin.this, sportsactivityadmin.class))
        );
        findViewById(R.id.Conference).setOnClickListener(view ->
                startActivity(new Intent(admin.this, conferenceadmin.class))
        );
        findViewById(R.id.Cultural).setOnClickListener(view ->
                startActivity(new Intent(admin.this, culturaladmin.class))
        );
        findViewById(R.id.Placement).setOnClickListener(view ->
                startActivity(new Intent(admin.this, placementadmin.class))
        );
    }
}
