package dev.scratch.scheduler

import dev.scratch.scheduler.util.DateTimeFrame

object DateTimeTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val start = "2023-07-17T00:00:00.000-04:00"
        val end = "2023-07-17T01:00:00.000-04:00"
        val datetimeFrame = DateTimeFrame(start, end)
        println(datetimeFrame.endDateTime.hour)
    }

}