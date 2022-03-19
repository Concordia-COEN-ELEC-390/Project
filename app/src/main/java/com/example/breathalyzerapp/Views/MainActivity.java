package com.example.breathalyzerapp.Views;

import androidx.appcompat.app.AppCompatActivity;

import com.example.breathalyzerapp.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button toProfilesBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toProfilesBtn = (Button) findViewById(R.id.to_profiles_btn);
        toProfilesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfiles();
            }
        });

    }

    private void openProfiles() {
        Intent intent = new Intent(this, ProfilesListActivity.class);
        startActivity(intent);
    }

}