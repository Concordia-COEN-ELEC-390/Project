package com.example.breathalyzerapp.Models;

import java.util.Comparator;

public class User {
    protected Integer userID;   // Primary key for DB

    protected String firstname; // User's first name
    protected String lastname; // User's last name
    protected int age;          // User's age
    protected double weight;    // used to estimate how long until not drunk anymore
    protected String gender;    // used to estimate how long until not drunk anymore

    /*  Add anything else useful */

    public User() {
        this.userID = 0;
        this.firstname = "";
        this.lastname = "";
        this.age = 0;
        this.weight = 0;
        this.gender = "";
    }

    public User(Integer userID, String firstname, String lastname, int age, double weight, String gender) {
        this.userID = userID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
    }

    public User(String firstname, String lastname, int age, double weight, String gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    // MAY REPLACE WITH SEX, WILL NOT USE BOTH
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

