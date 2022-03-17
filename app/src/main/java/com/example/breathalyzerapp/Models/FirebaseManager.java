package com.example.breathalyzerapp.Models;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FirebaseManager {
    final private String DATA_PATH = "MQ3 Reading/Value";   // sensor reading path

    protected FirebaseDatabase fbDb;
    protected DatabaseReference dbRef;

    protected User currentUser;         // not implemented yet

    public FirebaseManager() {
        fbDb = FirebaseDatabase.getInstance();
        dbRef = fbDb.getReference(DATA_PATH);
    }

    public FirebaseDatabase getFbDb() {
        return fbDb;
    }

    public void setFbDb(FirebaseDatabase fbDb) {
        this.fbDb = fbDb;
    }

    public DatabaseReference getDbRef() {
        return dbRef;
    }

    public void setDbRef(DatabaseReference dbRef) {
        this.dbRef = dbRef;
    }


    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

}
