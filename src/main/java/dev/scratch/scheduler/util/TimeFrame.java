package dev.scratch.scheduler.util;

import java.time.LocalTime;
import java.util.Objects;

public final class TimeFrame implements Comparable<TimeFrame> {
    private final LocalTime start;
    private final LocalTime end;

    public TimeFrame(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalTime getStart() {
        return start;
    }


    public LocalTime getEnd() {
        return end;
    }

    public boolean violates(TimeFrame timeFrame) {
        return start.compareTo(timeFrame.getEnd()) <= 0 && end.compareTo(timeFrame.getStart()) >= 0;
    }

    public boolean violates2(TimeFrame timeFrame) {
        return start.compareTo(timeFrame.getEnd()) < 0 && end.compareTo(timeFrame.getStart()) > 0;
    }

    public TimeFrame add(int minutes) {
        return new TimeFrame(start.plusMinutes(minutes), end.plusMinutes(minutes));
    }

    public TimeFrame subtract(int minutes) {
        return new TimeFrame(start.minusMinutes(minutes), end.minusMinutes(minutes));
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
        if (this.start.compareTo(o.start) != 0) {
            return this.start.compareTo(o.start);
        }
        return this.end.compareTo(o.end);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", start, end);
    }
}
