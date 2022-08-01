package dev.scratch.scheduler.generators;

import dev.scratch.scheduler.model.actions.SoftAction;
import dev.scratch.scheduler.service.ScheduleService;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public abstract class Generator {
    private final ScheduleService scheduleService;

    public Generator(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    public abstract Map<DayOfWeek, List<SoftAction>> generate();

    public ScheduleService getScheduleService() {
        return scheduleService;
    }
}
