package com.example.gpgondia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class NoticeActivity extends AppCompatActivity {

    TextView tvAnnouncementTitle, tvAnnouncementMessage;
    Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        tvAnnouncementTitle = findViewById(R.id.tvnoticeTitle);
        tvAnnouncementMessage = findViewById(R.id.tvAnnouncementMessage);
        btnClose = findViewById(R.id.btnClose);

        // Sample dynamic data (Can be replaced with data from API or database)
        String announcementTitle = "System Maintenance";
        String announcementMessage = "The system will be down for maintenance from 2 AM to 4 AM tonight.";

        tvAnnouncementTitle.setText(announcementTitle);
        tvAnnouncementMessage.setText(announcementMessage);

        // Close button event
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Closes the activity
            }
        });
    }
}
