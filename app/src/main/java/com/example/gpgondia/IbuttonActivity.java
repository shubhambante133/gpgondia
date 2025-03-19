package com.example.gpgondia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class IbuttonActivity extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        // Initialize UI elements
        TextView textAppName = view.findViewById(R.id.textAppName);
        Button btnVisitWebsite = view.findViewById(R.id.btnVisitWebsite);
        Button btnContactSupport = view.findViewById(R.id.btnContactSupport);

        textAppName.setText("GPGondia College App");

        btnVisitWebsite.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gpgondia.ac.in"));
            startActivity(browserIntent);
        });

        btnContactSupport.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:support@gpgondia.ac.in"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Support Request");
            startActivity(emailIntent);
        });

        return view;
    }
}
