package dev.scratch.scheduler.model.actions;

import java.time.DayOfWeek;
import java.time.Duration;

public class SoftAction extends Action {
    private Duration duration;

    public SoftAction(Duration duration, String content, DayOfWeek[] days) {
        super(content, days);
        this.duration = duration;
    }

    public Duration getDuration() {
        return duration;
    }

    public SoftAction setDuration(Duration duration) {
        this.duration = duration;
        return this;
    }
}
