package dev.scratch.scheduler.model;

import dev.scratch.scheduler.model.actions.HardAction;
import dev.scratch.scheduler.util.TimeFrame;

import java.time.DayOfWeek;

public class Constraint extends HardAction {
    public Constraint(String content, TimeFrame timeFrame, DayOfWeek[] days) {
        super(content, timeFrame, days);
    }
}
