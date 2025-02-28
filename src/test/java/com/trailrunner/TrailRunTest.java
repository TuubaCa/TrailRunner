package com.trailrunner;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for TrailRun.
 * Denna klass testar TrailRun.
 */
public class TrailRunTest {

    private TrailRun trailRun;

    private final double distance = 15.0;
    private final int durationInSeconds = 5400; // 1 hour and 30 minutes
    private Date date;
    private final String expectedFormattedDuration = "1:30:00";

    @BeforeEach
    public void setup() {
        date = createDate(2024, 12, 25);
        trailRun = new TrailRun(distance, durationInSeconds, date);
    }

    @Test
    public void shouldStoreCorrectInformation() {
        // Verify that the distance, date, and identifier are stored correctly.
        assertEquals(distance, trailRun.getDistance(), "Distance is not stored correctly");
        assertEquals(date, truncateTime(trailRun.getDate()), "Date is not stored correctly");
        assertEquals(10, trailRun.getRunIdentifier().length(), "RunIdentifier is not stored correctly");
    }

    @Test
    public void shouldCalculateAverageSpeed() {
        // Verify that the average speed is calculated correctly in km/h.
        double expectedAverageSpeed = distance / (durationInSeconds / 3600.0); // km/h
        assertEquals(expectedAverageSpeed, trailRun.getAverageSpeedPerHour(), 0.01, "Average speed is incorrect");
    }

    @Test
    public void shouldCalculateMinutesPerKilometer() {
        // Verify that minutes per kilometer is calculated correctly.
        double expectedMinutesPerKm = (durationInSeconds / 60.0) / distance;
        assertEquals(expectedMinutesPerKm, trailRun.getMinutesPerKilometer(), 0.01, "Minutes per kilometer is incorrect");
    }

    @Test
    public void shouldSetDefaultDateIfNull() {
        // Verify that the default date is set to today if no date is provided.
        TrailRun runWithoutDate = new TrailRun(12.0, 3600, (Date) null);
        assertNotNull(runWithoutDate.getDate(), "Default date should not be null");
        assertEquals(truncateTime(new Date()), truncateTime(runWithoutDate.getDate()), "Default date should be today if not provided");
    }

    
    private Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1); // Months are 0-based in Calendar
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    
    private Date truncateTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
