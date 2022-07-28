package dev.scratch.scheduler.model;

import dev.scratch.scheduler.model.actions.Action;
import dev.scratch.scheduler.model.actions.HardAction;
import dev.scratch.scheduler.util.TimeFrame;

import java.time.LocalTime;
import java.util.*;

public class Schedule {
    private final Map<TimeFrame, Action> schedule;
    private int timeBetweenActions;
    private List<Constraint> constraints;

    public Schedule() {
        this.schedule = new TreeMap<>();
        constraints = new LinkedList<>();
        timeBetweenActions = 30;
    }

    public Schedule(int timeBetweenActions) {
        this.schedule = new TreeMap<>();
        this.timeBetweenActions = timeBetweenActions;
    }

    public void addHardAction(HardAction action) {
        schedule.put(action.getTimeFrame(), action);
    }

    public Map<TimeFrame, Action> getSchedule() {
        return schedule;
    }

    public List<TimeFrame> getAvailableTimeFrames(int duration, int increments) {
        List<TimeFrame> availableTimeFrames = new ArrayList<>();
        TimeFrame timeFrame = new TimeFrame(LocalTime.MIDNIGHT, LocalTime.MIDNIGHT.plusMinutes(duration));
        while (timeFrame.getStart().compareTo(LocalTime.MIDNIGHT.minusMinutes(duration)) < 0) {
            boolean isFree = true;
            for (Map.Entry<TimeFrame, Action> entry : schedule.entrySet()) {
                if (timeFrame.overlaps(entry.getKey()) || timeFrame.violates(entry.getKey().add(timeBetweenActions)) || timeFrame.violates(entry.getKey().subtract(timeBetweenActions))) {
                    isFree = false;
                    break;
                }
            }
            for (Constraint constraint : constraints) {
                if (timeFrame.violates(constraint.getTimeFrame())) {
                    isFree = false;
                    break;
                }
            }

            if (isFree) {
                availableTimeFrames.add(timeFrame);
            }
            timeFrame = timeFrame.add(increments);
        }

        return availableTimeFrames;
    }

    public void printSchedule() {
        for (Map.Entry<TimeFrame, Action> entry : schedule.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getContent());
        }
    }

    public void addConstraint(Constraint constraint) {
        constraints.add(constraint);
    }

}
