package dev.scratch.scheduler.model.actions;

import java.time.DayOfWeek;

public abstract class Action {
    private String content;
    private DayOfWeek[] days;

    public Action(String content, DayOfWeek[] days) {
        this.content = content;
        this.days = days;
    }

    public String getContent() {
        return content;
    }

    public Action setContent(String content) {
        this.content = content;
        return this;
    }

    public DayOfWeek[] getDays() {
        return days;
    }

    public Action setDays(DayOfWeek[] days) {
        this.days = days;
        return this;
    }
}
