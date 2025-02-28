package com.trailrunner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class RunManager {

    private final List<TrailRun> runList = new ArrayList<>();

    public void addNewTrailRun(TrailRun run) {
        runList.add(run);
    }

    public TrailRun getTrailRun(String id) {
        return runList.stream()
                .filter(run -> run.getRunIdentifier().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void deleteTrailRun(String id) {
        runList.removeIf(run -> run.getRunIdentifier().equals(id));
    }

    public boolean isRunListEmpty() {
        return runList.isEmpty();
    }

    public int daysSinceLastRun() {
        if (isRunListEmpty()) {
            return 0;
        }

        Date today = new Date();
        return runList.stream()
                .mapToInt(run -> calculateDaysBetween(run.getDate(), today))
                .min()
                .orElse(0);
    }

    public TrailRun getLatestRun() {
        if (isRunListEmpty()) {
            return null;
        }

        return runList.stream()
                .max(Comparator.comparing(TrailRun::getDate))
                .orElse(null);
    }

    public double getTotalDistance() {
        return runList.stream()
                .mapToDouble(TrailRun::getDistance)
                .sum();
    }

    public double getAverageDistance() {
        if (isRunListEmpty()) {
            return 0.0;
        }
        return getTotalDistance() / getNumberOfRuns();
    }

    public int getNumberOfRuns() {
        return runList.size();
    }

    public Set<String> getSetOfIds() {
        return runList.stream()
                .map(TrailRun::getRunIdentifier)
                .collect(Collectors.toSet());
    }

    private int calculateDaysBetween(Date start, Date end) {
        long diffInMillies = Math.abs(end.getTime() - start.getTime());
        return (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
