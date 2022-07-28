package dev.scratch.scheduler.util;

import java.time.LocalTime;
import java.util.Objects;

public class TimeFrame implements Comparable<TimeFrame> {
    private LocalTime start;
    private LocalTime end;

    public TimeFrame(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalTime getStart() {
        return start;
    }

    public TimeFrame setStart(LocalTime start) {
        this.start = start;
        return this;
    }

    public LocalTime getEnd() {
        return end;
    }

    public TimeFrame setEnd(LocalTime end) {
        this.end = end;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeFrame timeFrame = (TimeFrame) o;
        return Objects.equals(start, timeFrame.start) && Objects.equals(end, timeFrame.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public int compareTo(TimeFrame o) {
        return this.start.compareTo(o.start);
    }
}
