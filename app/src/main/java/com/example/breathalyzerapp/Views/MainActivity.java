package com.example.breathalyzerapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.breathalyzerapp.Models.FirebaseManager;
import com.example.breathalyzerapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView temp;                                      // displays the sensor reading
    private Button toProfilesBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temp = findViewById(R.id.reading_text);

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