package com.example.breathalyzerapp.Models;

public class User {
    protected String name;      // User's name
    protected int age;          // User's age
    protected double weight;    // used to estimate how long until not drunk anymore
    protected String gender;    // used to estimate how long until not drunk anymore

    /*  Add anything else useful */

    public User() {
        this.name = "";
        this.age = 0;
        this.weight = 0;
        this.gender = "";
    }

    public User(String name, int age, double weight, String gender) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
