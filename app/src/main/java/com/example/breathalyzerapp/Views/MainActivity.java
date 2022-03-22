package com.example.breathalyzerapp.Views;

import androidx.appcompat.app.AppCompatActivity;

import com.example.breathalyzerapp.R;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button toProfilesBtn, toAboutBtn;
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        toProfilesBtn = (Button) findViewById(R.id.to_profiles_btn);
        toProfilesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfiles();
            }
        });

        toAboutBtn = (Button) findViewById(R.id.to_about_btn);
        toAboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAbout();
            }
        });

    }

    private void openProfiles() {
        Intent intent = new Intent(this, ProfilesListActivity.class);
        startActivity(intent);
    }

    private void openAbout() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

}