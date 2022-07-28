package dev.scratch.scheduler.service;

import dev.scratch.scheduler.model.Constraint;
import dev.scratch.scheduler.model.Schedule;
import dev.scratch.scheduler.model.actions.Action;
import dev.scratch.scheduler.model.actions.HardAction;
import dev.scratch.scheduler.model.actions.SoftAction;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ScheduleService {
    private final Queue<SoftAction> softActionQueue;
    private final Queue<HardAction> hardActionQueue;
    private final Queue<Constraint> constraints;
    private final Map<DayOfWeek, Schedule> schedules;

    public ScheduleService() {
        softActionQueue = new LinkedList<>();
        hardActionQueue = new LinkedList<>();
        constraints = new LinkedList<>();
        schedules = new HashMap<>();
        for (DayOfWeek day : DayOfWeek.values()) {
            schedules.put(day, new Schedule());
        }
    }

    public void addAction(Action action) {
        if (action instanceof SoftAction) {
            softActionQueue.add((SoftAction) action);
        } else {
            hardActionQueue.add((HardAction) action);
        }
    }

    public void addConstraint(Constraint constraint) {
        constraints.add(constraint);
    }

    public void schedule() {
        while (!hardActionQueue.isEmpty()) {
            HardAction action = hardActionQueue.remove();
            for (DayOfWeek day : action.getDays()) {
                schedules.get(day).addHardAction(action);
            }
        }
        while (!constraints.isEmpty()) {
            Constraint constraint = constraints.remove();
            for (DayOfWeek day : constraint.getDays()) {
                schedules.get(day).addConstraint(constraint);
            }
        }
    }

    public Schedule getSchedule(DayOfWeek day) {
        return schedules.get(day);
    }
}
