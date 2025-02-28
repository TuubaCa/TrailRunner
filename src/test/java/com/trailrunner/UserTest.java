package com.trailrunner;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for User.
 * Denna klass testar User.
 */
public class UserTest {

    private User user;

    @BeforeEach
    public void setup() {
        // Initialize user with basic information 
        user = new User(175.0f, 70.0f, 25);
    }

    @Test
    public void shouldInitializeUserWithCorrectValues() {
        // Kontrollera att användarens initialvärden är korrekta 
        assertEquals(175.0f, user.getHeight(), "Height should be correctly initialized");
        assertEquals(70.0f, user.getWeight(), "Weight should be correctly initialized");
        assertEquals(25, user.getAge(), "Age should be correctly initialized");
    }

    @Test
    public void fitnessScoreShouldBeZeroAtStart() {
        // Fitness score should be 0 at the start 
        assertEquals("0.00", user.calculateFitnessScore(), "Fitness score should be 0.00 initially");
    }

    @Test
    public void daysSinceLastRunShouldBeZeroInitially() {
        // Om ingen löpning har lagts till ska dagarna vara 0 
        assertEquals(0, user.daysSinceLastRun(), "Days since last run should be 0 initially");
    }

    @Test
    public void shouldAddRunAndUpdateValues() {
        // Create a new run and add it to the user 
        TrailRun newRun = new TrailRun(10.0, 3600, Date.valueOf(LocalDate.of(2025, 1, 8)));
        user.addNewRun(newRun);
    
        // Kontrollera antal löpningar 
        int expectedRunCount = 1;
        int actualRunCount = user.getRunCount();
        assertEquals(expectedRunCount, actualRunCount, "Run count should increase after adding a run");
    
        // Verify total distance 
        double expectedTotalDistance = 10.0;
        double actualTotalDistance = user.getTotalDistance();
        assertEquals(expectedTotalDistance, actualTotalDistance, 0.01, "Total distance should be updated correctly");
    }

    @Test
    public void averageDistanceShouldBeCorrect() {
        // Lägg till flera löpningar och kontrollera medeldistansen 
        TrailRun run1 = new TrailRun(10.0, 3600, Date.valueOf(LocalDate.of(2025, 1, 8)));
        TrailRun run2 = new TrailRun(5.0, 1800, Date.valueOf(LocalDate.of(2025, 1, 10)));
        
        user.addNewRun(run1);
        user.addNewRun(run2);
    
        // Calculate expected average distance 
        double expectedAverage = (10.0 + 5.0) / 2; // Total distance / Number of runs
        double actualAverage = user.getAverageDistance();
    
        // Kontrollera medeldistansen 
        assertEquals(expectedAverage, actualAverage, 0.01, "Average distance should be correctly calculated");
    }

    @Test
    public void fitnessScoreShouldNotBeNegative() {
        // Add an old run and verify that fitness score is not negative 
        TrailRun oldRun = new TrailRun(8.0, 2400, Date.valueOf(LocalDate.of(2025, 1, 1)));
        user.addNewRun(oldRun);

        // Beräkna fitnesspoäng (Svenska)
        String fitnessScore = user.calculateFitnessScore();

        // Fitness score should not be null 
        assertNotNull(fitnessScore, "Fitness score should not be null");

        // Kontrollera att fitnesspoäng är ett giltigt tal och inte negativ 
        assertDoesNotThrow(() -> {
            double parsedScore = Double.parseDouble(fitnessScore.replace(",", "."));
            assertTrue(parsedScore >= 0, "Fitness score should never be negative");
        }, "Fitness score should be a valid number");
    }
}
