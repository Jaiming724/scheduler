package dev.scratch.scheduler;

import dev.scratch.scheduler.model.actions.HardAction;
import dev.scratch.scheduler.service.ScheduleService;
import dev.scratch.scheduler.util.TimeFrame;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Tests {
    public static void main(String[] args) {

        LocalTime localTime1 = LocalTime.of(10, 0, 0);
        LocalTime localTime2 = LocalTime.of(10, 30, 0);
        LocalTime localTime3 = LocalTime.of(11, 0, 0);
        LocalTime localTime4 = LocalTime.of(12, 45, 0);
        TimeFrame timeFrame1 = new TimeFrame(localTime1, localTime2);
        TimeFrame timeFrame2 = new TimeFrame(localTime3, localTime4);


        ScheduleService scheduleService = new ScheduleService();
        HardAction hardAction1 = new HardAction("Lunch", timeFrame2, new DayOfWeek[]{DayOfWeek.MONDAY});
        HardAction hardAction2 = new HardAction("Morning Class", timeFrame1, new DayOfWeek[]{DayOfWeek.MONDAY});
        scheduleService.addActionForQueue(hardAction1);
        scheduleService.addActionForQueue(hardAction2);
        scheduleService.schedule();
        scheduleService.printSchedule();
    }
}
