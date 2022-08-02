package dev.scratch.scheduler.model.actions;

import dev.scratch.scheduler.util.TimeFrame;

import java.time.DayOfWeek;

public class HardAction extends Action {
    private TimeFrame timeFrame;

    public HardAction(String content, TimeFrame timeFrame, DayOfWeek[] days) {
        super(content, days);
        this.timeFrame = timeFrame;
    }

    public HardAction(String content, TimeFrame timeFrame, DayOfWeek[] days, int intervalBefore, int intervalAfter) {
        super(content, days, intervalBefore, intervalAfter);
        this.timeFrame = timeFrame;
    }

    public TimeFrame getTimeFrame() {
        return timeFrame;
    }

    public HardAction setTimeFrame(TimeFrame timeFrame) {
        this.timeFrame = timeFrame;
        return this;
    }


}
