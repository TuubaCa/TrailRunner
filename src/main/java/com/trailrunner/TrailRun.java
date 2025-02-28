package com.trailrunner;

import java.security.SecureRandom;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class TrailRun {

    private String runIdentifier; // TrailMaster ID
    private double distance; // Distans (km)
    private int durationInSeconds; // Tid (i sekunder)
    private Date date; // Datum

    private double medelhastighet; //ortalama hizi hesapliyor
    private double kilometertid; //bir kilometreyi ne kadar surede aldigini hesapliyor
    

    
    public TrailRun(double distance, int durationInSeconds, Date date) {
        this.runIdentifier = generateRandomId();
        this.distance = distance;
        this.durationInSeconds = durationInSeconds;
        this.date = (date != null) ? date : new Date(); // Om inget datum anges, används dagens datum.
        this.medelhastighet=distance/(durationInSeconds/3600);
        this.kilometertid=(durationInSeconds/60)/distance;
    }

   

    
    public double getAverageSpeedPerHour() {
        double hours = durationInSeconds / 3600.0;
        return distance / hours;
    }

    
    public double getMinutesPerKilometer() {
        double minutes = durationInSeconds / 60.0;
        return minutes / distance;
    }

    // Getter-metoder (Getters)

    public String getRunIdentifier() {
        return runIdentifier;
    }

    public double getDistance() {
        return distance;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public Date getDate() {
        return date;
    }

    
    @Override
    public String toString() {
        return String.format("Run ID: %s, Distans: %.2f km, Tid: %s, Datum: %s",
                runIdentifier, distance, formatDuration(), date);
    }

    
    private String formatDuration() {
        long hours = TimeUnit.SECONDS.toHours(durationInSeconds);
        long minutes = TimeUnit.SECONDS.toMinutes(durationInSeconds) % 60;
        long seconds = durationInSeconds % 60;
        return String.format("%d:%02d:%02d", hours, minutes, seconds);
    }

    private String generateRandomId() {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int ID_LENGTH = 10;
        SecureRandom random = new SecureRandom();
        
        StringBuilder sb = new StringBuilder(ID_LENGTH);
        for (int i = 0; i < ID_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}
