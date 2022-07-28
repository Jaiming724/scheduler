package dev.scratch.scheduler.service;

import dev.scratch.scheduler.model.Constraint;
import dev.scratch.scheduler.model.Schedule;
import dev.scratch.scheduler.model.actions.Action;
import dev.scratch.scheduler.model.actions.HardAction;
import dev.scratch.scheduler.model.actions.SoftAction;
import dev.scratch.scheduler.util.TimeFrame;

import java.time.DayOfWeek;
import java.util.*;

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

    public Map<DayOfWeek, List<SoftAction>> schedule() {
        Map<DayOfWeek, List<SoftAction>> unavailableActions = new HashMap<>();
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
        while (!softActionQueue.isEmpty()) {
            SoftAction softAction = softActionQueue.remove();
            for (DayOfWeek day : softAction.getDays()) {
                Schedule currentSchedule = schedules.get(day);
                List<TimeFrame> availableTimeFrames = currentSchedule.getAvailableTimeFrames((int) softAction.getDuration().toMinutes(), 15);
                if (availableTimeFrames.size() == 0) {
                    unavailableActions.computeIfAbsent(day, k -> new ArrayList<>());
                    unavailableActions.get(day).add(softAction);
                    System.out.println("No available time on " + day + " for " + softAction.getContent());
                    break;
                }
                int index = (int) (Math.random() * availableTimeFrames.size());
                TimeFrame timeFrame = availableTimeFrames.get(index);
                HardAction hardAction = new HardAction(softAction.getContent(), timeFrame, softAction.getDays());
                currentSchedule.getSchedule().put(timeFrame, hardAction);
            }
        }
        return unavailableActions;
    }

    public Schedule getSchedule(DayOfWeek day) {
        return schedules.get(day);
    }
}
