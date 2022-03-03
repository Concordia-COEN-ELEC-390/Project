package com.example.breathalyzerapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.breathalyzerapp.Models.FirebaseManager;
import com.example.breathalyzerapp.Models.User;
import com.example.breathalyzerapp.Models.sortUsersByFirstName;
import com.example.breathalyzerapp.R;
import com.example.breathalyzerapp.SQLiteDatabase.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // UI variables
    protected Toolbar mainToolbar;
    protected FloatingActionButton mainFloatingActionButton;    // on click, opens dialogue fragment to create profile
    private TextView temp;                                      // displays the sensor reading
    protected ListView mainListView;                            // displays all profiles in a LV
    protected ArrayAdapter arrayAdapter;                        // click on a profile to access it

    // Database variables
    protected DatabaseHelper dbHelper;
    protected List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();

        /* read live sensor data example */
        read();

    }

    // populate the UI
    void setupUI() {
        setViewByIDs();
        setSupportActionBar(mainToolbar);

        loadUsersListView();
    }

    // sets objects with IDs from resources
    private void setViewByIDs(){
        mainToolbar = findViewById(R.id.main_toolbar);
        mainFloatingActionButton = findViewById(R.id.mainFloatingActionButton);
        mainListView = findViewById(R.id.data_activity_ListView1);

        temp = findViewById(R.id.main_last_reading_tv);
    }

    protected void loadUsersListView(){
        dbHelper = new DatabaseHelper(this);
        userList = dbHelper.getAllUsers();

        // sort names list by surname alphabetically
        Collections.sort(userList,new sortUsersByFirstName());

        ArrayList<String> namesListText = new ArrayList<>();

        for(int i =0; i< userList.size();i++){
            String nameLine = "";

            nameLine += i+1 +".\t\t " + userList.get(i).getFirstname() + ", " + userList.get(i).getLastname();

            namesListText.add(nameLine);

        }

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,namesListText);
        mainListView.setAdapter(arrayAdapter);
    }

    // read from the sensor
    private void read() {
        FirebaseManager firebase = new FirebaseManager();   // switching to SQLite

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