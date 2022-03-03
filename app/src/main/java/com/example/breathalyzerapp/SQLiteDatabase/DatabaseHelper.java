package com.example.breathalyzerapp.SQLiteDatabase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.breathalyzerapp.Models.Sex;
import com.example.breathalyzerapp.Models.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String TAG = "DatabaseHelper";

    public DatabaseHelper(@Nullable Context context) {
        super(context, Config.DATABASE_NAME, null, Config.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_PROFILE = "CREATE TABLE " + Config.PROFILE_TABLE_NAME +
                " (" + Config.COLUMN_PROFILE_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_FIRSTNAME + " TEXT NOT NULL, " + Config.COLUMN_LASTNAME + " TEXT NOT NULL, "
                + Config.COLUMN_AGE + " TEXT NOT NULL, " + Config.COLUMN_SEX + " TEXT NOT NULL, " + Config.COLUMN_WEIGHT + " TEXT NOT NULL)";

        Log.d(TAG,CREATE_TABLE_PROFILE);

        sqLiteDatabase.execSQL(CREATE_TABLE_PROFILE);
        Log.d(TAG, "Profile table created");
        //Toast.makeText(context,"Profile table created", Toast.LENGTH_LONG).show();        // debug

        String CREATE_TABLE_ACCESS = "CREATE TABLE " + Config.READINGS_TABLE_NAME +
                " (" + Config.COLUMN_READINGS_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_READINGS_VALUE + " TEXT NOT NULL, " + Config.COLUMN_TIMESTAMP + " TEXT NOT NULL)";

        Log.d(TAG,CREATE_TABLE_ACCESS);

        sqLiteDatabase.execSQL(CREATE_TABLE_ACCESS);
        Log.d(TAG, "Readings table created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Nothing yet
    }

    public long insertUser(User user){
        long id = -1;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Config.COLUMN_PROFILE_ID, user.getUserID());
        contentValues.put(Config.COLUMN_FIRSTNAME, user.getFirstname());
        contentValues.put(Config.COLUMN_LASTNAME, user.getLastname());
        contentValues.put(Config.COLUMN_AGE, user.getAge());
        contentValues.put(Config.COLUMN_SEX, user.getGender());    // sex/gender tbd
        contentValues.put(Config.COLUMN_WEIGHT, user.getWeight());

        try {
            id = db.insertOrThrow(Config.PROFILE_TABLE_NAME,null,contentValues);
        }
        catch (SQLiteException e){
            Log.d(TAG,"EXCEPTION" + e);
            //Toast.makeText(context,"Operation Failed!: " + e, Toast.LENGTH_LONG).show();
        }
        finally {
            db.close();
        }
        return id;
    }

    public List<User> getAllUsers(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        try{
            cursor =db.query(Config.PROFILE_TABLE_NAME,null,null,null,null,null,null);

            if(cursor != null){
                if(cursor.moveToFirst()){
                    List<User> userList = new ArrayList<>();

                    do {
                        @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_PROFILE_ID));
                        @SuppressLint("Range") String firstname = cursor.getString(cursor.getColumnIndex(Config.COLUMN_FIRSTNAME));
                        @SuppressLint("Range") String lastname = cursor.getString(cursor.getColumnIndex(Config.COLUMN_LASTNAME));
                        @SuppressLint("Range") int age = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_AGE));
                        @SuppressLint("Range") double weight = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_WEIGHT));
                        @SuppressLint("Range") String gender = cursor.getString(cursor.getColumnIndex(Config.COLUMN_SEX));

                        userList.add(new User(id,firstname,lastname,age,weight,gender));
                    }
                    while (cursor.moveToNext());

                    return userList;
                }
            }
        }
        catch (SQLiteException e)
        {
            Log.d(TAG,"EXCEPTION" + e);
            Toast.makeText(context,"Operation Failed!: " + e, Toast.LENGTH_LONG).show();
        }
        finally {
            if(cursor != null){
                cursor.close();
            }
            db.close();
        }
        return Collections.EMPTY_LIST;
    }

}
