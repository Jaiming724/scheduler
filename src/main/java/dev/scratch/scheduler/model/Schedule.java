package dev.scratch.scheduler.model;

import dev.scratch.scheduler.model.actions.Action;
import dev.scratch.scheduler.model.actions.HardAction;
import dev.scratch.scheduler.util.TimeFrame;

import java.util.Map;
import java.util.TreeMap;

public class Schedule {
    private final Map<TimeFrame, Action> schedule;

    public Schedule() {
        this.schedule = new TreeMap<>();
    }

    public void addHardAction(HardAction action) {
        schedule.put(action.getTimeFrame(), action);
    }

    public Map<TimeFrame, Action> getSchedule() {
        return schedule;
    }
}
