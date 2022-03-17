package com.example.breathalyzerapp.Models;

public class Reading {
    protected Integer readingID;    // Primary key for db

    protected Double readingValue;  // reading from sensor
    protected String timestamp;     // time reading was taken

    public Reading(Integer readingID, Double readingValue, String timestamp) {
        this.readingID = readingID;
        this.readingValue = readingValue;
        this.timestamp = timestamp;
    }

    public Reading(Double readingValue, String timestamp) {
        this.readingValue = readingValue;
        this.timestamp = timestamp;
    }

    public Integer getReadingID() {
        return readingID;
    }

    public void setReadingID(Integer readingID) {
        this.readingID = readingID;
    }

    public Double getReadingValue() {
        return readingValue;
    }

    public void setReadingValue(Double readingValue) {
        this.readingValue = readingValue;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}


