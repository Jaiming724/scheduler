package dev.scratch.scheduler;

import dev.scratch.scheduler.model.Constraint;
import dev.scratch.scheduler.model.Schedule;
import dev.scratch.scheduler.model.actions.HardAction;
import dev.scratch.scheduler.service.ScheduleService;
import dev.scratch.scheduler.util.TimeFrame;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Tests {
    public static void main(String[] args) {

        LocalTime localTime1 = LocalTime.of(10, 0, 0);
        LocalTime localTime2 = LocalTime.of(10, 30, 0);
        LocalTime localTime3 = LocalTime.of(10, 30, 0);
        LocalTime localTime4 = LocalTime.of(12, 30, 0);
        TimeFrame timeFrame1 = new TimeFrame(localTime1, localTime2);
        TimeFrame timeFrame2 = new TimeFrame(localTime3, localTime4);
        TimeFrame timeFrame3 = new TimeFrame(LocalTime.MIDNIGHT, LocalTime.of(9, 0, 0));
        TimeFrame timeFrame4 = new TimeFrame(LocalTime.of(14, 0, 0), LocalTime.of(23, 59, 59));
        System.out.println(timeFrame1.violates(timeFrame2));
        System.out.println(LocalTime.MIDNIGHT.compareTo(LocalTime.MIDNIGHT.minusMinutes(5)));

        ScheduleService scheduleService = new ScheduleService();
        HardAction hardAction1 = new HardAction("Lunch", timeFrame2, new DayOfWeek[]{DayOfWeek.MONDAY});
        HardAction hardAction2 = new HardAction("Morning Class", timeFrame1, new DayOfWeek[]{DayOfWeek.MONDAY});
        Constraint constraint1 = new Constraint("Sleep", timeFrame3, new DayOfWeek[]{DayOfWeek.MONDAY});
        Constraint constraint2 = new Constraint("Break", timeFrame4, new DayOfWeek[]{DayOfWeek.MONDAY});
        scheduleService.addAction(hardAction1);
        scheduleService.addAction(hardAction2);
        scheduleService.addConstraint(constraint1);
        scheduleService.addConstraint(constraint2);
        scheduleService.schedule();
        Schedule mondaySchedule = scheduleService.getSchedule(DayOfWeek.MONDAY);
        mondaySchedule.printSchedule();
        for (TimeFrame timeFrame : mondaySchedule.getAvailableTimeFrames(30, 15)) {
            System.out.println(timeFrame);
        }
    }
}
