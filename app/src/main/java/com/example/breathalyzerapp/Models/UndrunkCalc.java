package com.example.breathalyzerapp.Models;

public class UndrunkCalc {
    private double initialBAC;
    private double currentBAC;
    private int age;
    private int weight;         // in kg
    private Sex gender;

    public UndrunkCalc() {
        this.initialBAC = 0.0;
        this.currentBAC = 0.0;
        this.age = 24;
        this.weight = 75;
        this.gender = Sex.MALE;
    }

    public UndrunkCalc(double currentBAC, int age, int weight, Sex gender) {
        this.initialBAC = currentBAC;
        this.currentBAC = currentBAC;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
    }

    public double getEstimatedLegalTime() {
        /* assumes legal limit of 0.08, also a rough estimate not taking much into account
        * source is https://www.bgsu.edu/recwell/wellness-connection/alcohol-education/alcohol-metabolism.html#:~:text=Alcohol%20leaves%20the%20body%20at,one%20standard%20drink%20per%20hour.
        *  */
        double timeInHours = 0;
        double rate = -0.015;

        while (currentBAC > 0.08) {
            timeInHours++;
            currentBAC += rate;
        }

        return timeInHours;
    }

    public double getInitialBAC() {
        return this.initialBAC;
    }
}
