package com.trailrunner;

import java.text.DecimalFormat;


public class User {

    private float height; // Längd i cm
    private float weight; // Vikt i kg
    private int age; // Ålder

    private final RunManager runManager; // Hanterar löpsessioner
    private double fitnessScore; // Fitnesspoäng

   
    public User(float height, float weight, int age) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.fitnessScore = 0.0;
        this.runManager = new RunManager();
    }

    // Getter och Setter-metoder

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    
    public String calculateFitnessScore() {
        if (runManager.isRunListEmpty()) {
            return "0.00";
        }

        TrailRun latestRun = getLatestRun();

        double calculatedScore = (latestRun.getDistance() + (latestRun.getAverageSpeedPerHour() / latestRun.getMinutesPerKilometer())) - (daysSinceLastRun() / 2.0);
    
        if (fitnessScore == 0.0) {
            fitnessScore = Math.max(calculatedScore, 0.0); // Negatif olmaması için
        } else {
            fitnessScore = Math.max(fitnessScore + calculatedScore, 0.0);
        }

        //check its consistency
        // if (fitnessScore == 0.0) {
        //     fitnessScore = (latestRun.getDistance() + (latestRun.getAverageSpeedPerHour() / latestRun.getMinutesPerKilometer())) - (daysSinceLastRun() / 2.0);
        // } else {
        //     fitnessScore = Math.max(
        //         fitnessScore + (latestRun.getDistance() + (latestRun.getAverageSpeedPerHour() / latestRun.getMinutesPerKilometer())) - (daysSinceLastRun() / 2.0),
        //         0.0
        //     );
        // }

        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return decimalFormat.format(fitnessScore);
    }

    
    public int getRunCount() {
        return runManager.getNumberOfRuns();
    }

    
    public int daysSinceLastRun() {
        return runManager.daysSinceLastRun();
    }

    //userin son kosusunu dondurur
    private TrailRun getLatestRun() {
        return runManager.getLatestRun();
    }

    
    public void addNewRun(TrailRun newRun) {
        runManager.addNewTrailRun(newRun);
    }

    
    public double getTotalDistance() {
        return runManager.getTotalDistance();
    }

    
    public double getAverageDistance() {
        return runManager.getAverageDistance();
    }
}