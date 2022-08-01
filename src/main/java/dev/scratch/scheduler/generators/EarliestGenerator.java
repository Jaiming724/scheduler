package dev.scratch.scheduler.generators;

import dev.scratch.scheduler.model.Schedule;
import dev.scratch.scheduler.model.actions.HardAction;
import dev.scratch.scheduler.model.actions.SoftAction;
import dev.scratch.scheduler.service.ScheduleService;
import dev.scratch.scheduler.util.TimeFrame;

import java.time.DayOfWeek;
import java.util.*;

public class EarliestGenerator extends Generator {

    public EarliestGenerator(ScheduleService scheduleService) {
        super(scheduleService);
    }

    @Override
    public Map<DayOfWeek, List<SoftAction>> generate() {
        Map<DayOfWeek, List<SoftAction>> unavailableActions = new HashMap<>();
        Queue<SoftAction> softActionQueue = getScheduleService().getSoftActionQueue();
        while (!softActionQueue.isEmpty()) {
            SoftAction softAction = softActionQueue.remove();
            for (DayOfWeek day : softAction.getDays()) {
                Schedule currentSchedule = getScheduleService().getSchedule(day);
                List<TimeFrame> availableTimeFrames = currentSchedule.getAvailableTimeFrames((int) softAction.getDuration().toMinutes(), 15);
                if (availableTimeFrames.size() == 0) {
                    unavailableActions.computeIfAbsent(day, k -> new ArrayList<>());
                    unavailableActions.get(day).add(softAction);
                    continue;
                }
                TimeFrame timeFrame = availableTimeFrames.get(0);
                HardAction hardAction = new HardAction(softAction.getContent(), timeFrame, softAction.getDays());
                currentSchedule.getSchedule().put(timeFrame, hardAction);
            }
        }
        return unavailableActions;
    }
}
