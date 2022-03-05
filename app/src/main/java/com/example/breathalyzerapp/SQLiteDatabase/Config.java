package com.example.breathalyzerapp.SQLiteDatabase;

public class Config {
    public static final String DATABASE_NAME = "profiles-db";
    public static final int DATABASE_VERSION = 1;

    // USER TABLE
    public static final String USER_TABLE_NAME = "profile";
    public static final String COLUMN_USER_ID = "profileID";                   // primary key
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_SEX= "sex";
    public static final String COLUMN_WEIGHT= "weight";


    // READINGS TABLE
    public static final String READINGS_TABLE_NAME = "readings";
    public static final String COLUMN_READINGS_ID = "readingsID";                 // primary key
    public static final String COLUMN_READINGS_VALUE = "readings_value";
    public static final String COLUMN_TIMESTAMP = "timestamp";                // time and date
}
