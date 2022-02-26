package com.example.breathalyzerapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.breathalyzerapp.Models.FirebaseManager;
import com.example.breathalyzerapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* read live sensor data example */
        temp = findViewById(R.id.temp);
        read();
    }

    private void read() {
        FirebaseManager firebase = new FirebaseManager();

        firebase.getDbRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Double sensorVal = snapshot.getValue(Double.class);
                temp.setText("Live reading: " + sensorVal);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}