package com.example.breathalyzerapp.Views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.breathalyzerapp.Models.FirebaseManager;
import com.example.breathalyzerapp.Models.Reading;
import com.example.breathalyzerapp.Models.Sex;
import com.example.breathalyzerapp.Models.UndrunkCalc;
import com.example.breathalyzerapp.R;
import com.example.breathalyzerapp.SQLiteDatabase.DatabaseHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class readingFragment extends DialogFragment {
    protected TextView reading_TV, reading_value_tv;
    protected Button readingCancelButton, readingsSaveButton, undrunkButton;
    protected double readingVal = 0.0;
    protected Calendar date;
    protected SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-YYYY, hh:mm");

    public readingFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_reading, container, false);
        read();

        date = Calendar.getInstance();


        // ViewByIDs
        reading_TV = view.findViewById(R.id.reading_tv);
        reading_value_tv = view.findViewById(R.id.reading_value_tv);
        readingCancelButton = view.findViewById(R.id.readingCancelButton);
        readingsSaveButton = view.findViewById(R.id.readingSaveButton);
        undrunkButton = view.findViewById(R.id.whenToDriveButton);


        // save button
        readingsSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
                databaseHelper.insertReading(new Reading(readingVal,dateFormatter.format(date.getTime())));
                ((ProfileActivity) getActivity()).loadReadingsListView();
                Objects.requireNonNull(getDialog()).dismiss();
            }
        });

        // cancel button
        readingCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getDialog()).dismiss();
            }
        });

        // Undrunk button
        undrunkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UndrunkCalc temp = new UndrunkCalc(readingVal, 24, 75, Sex.MALE);
                Intent intent = new Intent(getActivity(), UndrunkActivity.class);
                intent.putExtra("initialBAC", temp.getInitialBAC());
                intent.putExtra("timeEstimate", temp.getEstimatedLegalTime());
                startActivity(intent);
            }
        });

        return view;
    }

    private void read() {
        FirebaseManager firebase = new FirebaseManager();   // switching to SQLite // < -- yeah but this is just for the live reading

        firebase.getDbRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    Double sensorVal = snapshot.getValue(Double.class);
                    readingVal = (double) Math.round(sensorVal*1000) /1000;
                    String sensorString = Double.toString(readingVal);
                    reading_value_tv.setText(sensorString);
                } catch (NullPointerException npe){
                    reading_value_tv.setText("");
                    Toast.makeText(getActivity(), "Reading Issue... ",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}