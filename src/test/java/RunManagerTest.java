import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.trailrunner.RunManager;
import com.trailrunner.TrailRun;

public class RunManagerTest {

    private RunManager runManager;

    @BeforeEach
    public void setup() {
        runManager = new RunManager();
        runManager.addNewTrailRun(new TrailRun(5.0, 3000, createDate(2024, 12, 25)));
        runManager.addNewTrailRun(new TrailRun(7.0, 3600, createDate(2024, 12, 26)));
        runManager.addNewTrailRun(new TrailRun( 10.0, 4500, createDate(2024, 12, 27)));
    }

    @Test
    public void testAddRun() {
        int initialCount = runManager.getNumberOfRuns();
        runManager.addNewTrailRun(new TrailRun( 8.0, 4000, createDate(2024, 12, 28)));
        assertEquals(initialCount + 1, runManager.getNumberOfRuns());
    }

    @Test
    public void testGetLatestRun() {
        TrailRun latestRun = runManager.getLatestRun();
        assertNotNull(latestRun);
        assertEquals(createDate(2024, 12, 27), latestRun.getDate());
    }

    private Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
