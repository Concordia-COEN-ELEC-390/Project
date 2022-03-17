package com.example.breathalyzerapp.Views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.breathalyzerapp.Models.FirebaseManager;
import com.example.breathalyzerapp.Models.Reading;
import com.example.breathalyzerapp.R;
import com.example.breathalyzerapp.SQLiteDatabase.DatabaseHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class readingFragment extends DialogFragment {
    protected TextView reading_TV, reading_value_tv;
    protected Button readingCancelButton, readingsSaveButton;
    protected Double sensorVal = 0.0;   // DEFAULT

    public readingFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_reading, container, false);

        // ViewByIDs
        reading_TV = view.findViewById(R.id.reading_tv);
        reading_value_tv = view.findViewById(R.id.reading_value_tv);
        readingCancelButton = view.findViewById(R.id.readingCancelButton);
        readingsSaveButton = view.findViewById(R.id.readingSaveButton);

        FirebaseManager firebase = new FirebaseManager();   // switching to SQLite

        firebase.getDbRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sensorVal = snapshot.getValue(Double.class);    //TODO: this is returning null for me
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        reading_value_tv.setText(sensorVal.toString());
        // TOAST DEBUG - CAN REMOVE WHEN WE GET A VALUE
        Toast.makeText(getActivity(), "Reading: "+sensorVal,Toast.LENGTH_LONG).show();


        // save button
        readingsSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
                // temporarily hardcoded to make sure it works
                databaseHelper.insertReading(new Reading(1.0,"Date"));
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

        return view;
    }

}