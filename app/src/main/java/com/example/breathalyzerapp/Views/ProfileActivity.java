package com.example.breathalyzerapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.breathalyzerapp.Models.FirebaseManager;
import com.example.breathalyzerapp.Models.Reading;
import com.example.breathalyzerapp.Models.User;
import com.example.breathalyzerapp.Models.sortUsersByFirstName;
import com.example.breathalyzerapp.R;
import com.example.breathalyzerapp.SQLiteDatabase.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TODO: MAKE NEW TOOLBAR AND CHANGE TV TO LOGO

public class ProfileActivity extends AppCompatActivity {

    // UI variables
    protected Toolbar mainToolbar;
    protected TextView profile_name_tv;                         // holds the profile name
    protected ListView readingsListView;                        // displays all readings in a LV
    protected ArrayAdapter arrayAdapter;                        // click on a profile to access it
    protected Button new_reading_button;                        // button to take a new reading

    // Database variables
    protected DatabaseHelper dbHelper;
    protected List<Reading> readingsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setupUI();
    }

    // populate the UI
    void setupUI() {
        // get all resource ids
        setViewByIDs();
        // set up toolbar & navigation
        setSupportActionBar(mainToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        // setup the listview
        loadReadingsListView();

        // set up readings button
        new_reading_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open readings fragment
                readingFragment dialogueFragment = new readingFragment();
                dialogueFragment.show(getSupportFragmentManager(),"InsertReadingFragment");
            }
        });

    }

    // sets objects with IDs from resources
    private void setViewByIDs(){
        mainToolbar = findViewById(R.id.main_toolbar);
        profile_name_tv = findViewById(R.id.profileName_TV);
        readingsListView = findViewById(R.id.readings_history_LV);
        new_reading_button = findViewById(R.id.new_reading_button);
    }

    protected void loadReadingsListView(){
        dbHelper = new DatabaseHelper(this);
        readingsList = dbHelper.getAllReadings();

        // sort readings list by timestamp - should be done by default
        // Collections.sort(readingsList,new sortReadingsByTimestamp()); // sortReadingsByTimestamp() needs to be implemented if necessary

        ArrayList<String> readingsListText = new ArrayList<>();

        for(int i =0; i< readingsList.size();i++){
            String readingsLine = "";

            readingsLine += i+1 +".\t\t " + readingsList.get(i).getReadingValue() + ", " + readingsList.get(i).getTimestamp();

            readingsListText.add(readingsLine);

        }

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,readingsListText);
        readingsListView.setAdapter(arrayAdapter);
    }

}