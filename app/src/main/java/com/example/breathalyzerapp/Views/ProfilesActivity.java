package com.example.breathalyzerapp.Views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.breathalyzerapp.Models.User;
import com.example.breathalyzerapp.Models.sortUsersByFirstName;
import com.example.breathalyzerapp.R;
import com.example.breathalyzerapp.SQLiteDatabase.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TODO:
// IMPLEMENT ONCLICK FOR THE LISTVIEW TO GO TO THAT PROFILE AND SEE THE READINGS
// NEED A PROFILE ACTIVITY
// WE WILL THEN PASS THE PROFILE INFO AND READINGS TO THE ACTIVITY (LIKE IN ASSIGNMENT 2)

public class ProfilesActivity extends AppCompatActivity {

    // UI variables
    protected Toolbar mainToolbar;
    protected FloatingActionButton mainFloatingActionButton;    // on click, opens dialogue fragment to create profile
    protected ListView mainListView;                            // displays all profiles in a LV
    protected ArrayAdapter arrayAdapter;                        // click on a profile to access it

    // Database variables
    protected DatabaseHelper dbHelper;
    protected List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);

        setupUI();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // If there are no profiles saved, the user is prompted to save one
        if(arrayAdapter.isEmpty())
        {
            InsertUserDialogueFragment dialogueFragment = new InsertUserDialogueFragment();
            dialogueFragment.show(getSupportFragmentManager(),"InsertUserFragment");
        }

    }

    // populate the UI
    void setupUI() {
        setViewByIDs();
        setSupportActionBar(mainToolbar);

        ActionBar top_action_bar = getSupportActionBar();
        assert top_action_bar != null;
        top_action_bar.setDisplayHomeAsUpEnabled(true);

        loadUsersListView();

        mainFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertUserDialogueFragment dialogueFragment = new InsertUserDialogueFragment();
                dialogueFragment.show(getSupportFragmentManager(),"InsertUserFragment");
            }
        });
    }

    // sets objects with IDs from resources
    private void setViewByIDs(){
        mainToolbar = findViewById(R.id.main_toolbar);
        mainFloatingActionButton = findViewById(R.id.mainFloatingActionButton);
        mainListView = findViewById(R.id.data_activity_ListView1);
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

}

