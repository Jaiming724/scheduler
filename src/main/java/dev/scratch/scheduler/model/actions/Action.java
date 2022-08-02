package dev.scratch.scheduler.model.actions;

import java.time.DayOfWeek;

public abstract class Action {
    private String content;
    private DayOfWeek[] days;
    private int intervalBefore = 15;
    private int intervalAfter = 15;

    public Action(String content, DayOfWeek[] days) {
        this.content = content;
        this.days = days;
    }

    public Action(String content, DayOfWeek[] days, int intervalBefore, int intervalAfter) {
        this.content = content;
        this.days = days;
        this.intervalBefore = intervalBefore;
        this.intervalAfter = intervalAfter;
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

    public int getIntervalBefore() {
        return intervalBefore;
    }

    public int getIntervalAfter() {
        return intervalAfter;
    }
}
