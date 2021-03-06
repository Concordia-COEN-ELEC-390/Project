package com.example.breathalyzerapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TODO: MAKE NEW TOOLBAR AND CHANGE TV TO LOGO

public class ProfilesListActivity extends AppCompatActivity {

    // UI variables
    protected Toolbar mainToolbar;
    protected FloatingActionButton mainFloatingActionButton;    // on click, opens dialogue fragment to create profile
    protected ListView mainListView;                            // displays all profiles in a LV
    protected ArrayAdapter arrayAdapter;                        // click on a profile to access it

    // Database variables
    protected DatabaseHelper dbHelper;
    protected List<User> userList;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_profiles_list);

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
        loadUsersListView();

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

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


        // allows to navigate to profileview by clicking on the name in the list
       mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {// int i is position and long l is id
               Intent intent = new Intent(ProfilesListActivity.this, ProfileActivity.class);
               intent.putExtra("name", userList.get(i).getFirstname());
               startActivity(intent);
           }
       });
    }


}

