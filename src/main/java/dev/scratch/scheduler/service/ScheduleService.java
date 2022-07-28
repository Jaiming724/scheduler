package dev.scratch.scheduler.service;

import dev.scratch.scheduler.model.Schedule;
import dev.scratch.scheduler.model.actions.Action;
import dev.scratch.scheduler.model.actions.HardAction;
import dev.scratch.scheduler.model.actions.SoftAction;
import dev.scratch.scheduler.util.TimeFrame;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ScheduleService {
    private final Queue<SoftAction> softActionQueue;
    private final Queue<HardAction> hardActionQueue;
    private final Map<DayOfWeek, Schedule> schedules;


    public ScheduleService() {
        softActionQueue = new LinkedList<>();
        hardActionQueue = new LinkedList<>();
        schedules = new HashMap<>();
        for (DayOfWeek day : DayOfWeek.values()) {
            schedules.put(day, new Schedule());
        }
    }

    public void addActionForQueue(Action action) {
        if (action instanceof SoftAction) {
            softActionQueue.add((SoftAction) action);
        } else {
            hardActionQueue.add((HardAction) action);
        }
    }

    public void schedule() {
        while (!hardActionQueue.isEmpty()) {
            HardAction action = hardActionQueue.remove();
            for (DayOfWeek day : action.getDays()) {
                schedules.get(day).addHardAction(action);
            }
        }
    }

    public void printSchedule() {
        for (DayOfWeek day : DayOfWeek.values()) {
            Schedule schedule = schedules.get(day);
            for (Map.Entry<TimeFrame, Action> entry : schedule.getSchedule().entrySet()) {
                String time = String.format("%s - %s", entry.getKey().getStart(), entry.getKey().getEnd());
                System.out.println(time + " " + entry.getValue().getContent());
            }
        }
    }
}
