package dev.scratch.scheduler

import dev.scratch.scheduler.util.DateTimeOptional
import dev.scratch.scheduler.util.SimpleDate
import dev.scratch.scheduler.util.SimpleDateTime

object DateTimeTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val start = "2023-07-17T00:00:00.000-04:00"
        val end = "2023-07-17T01:00:00.000-04:00"
        val test = "2023-07-17"
        val datetime = DateTimeOptional(end)
        val date = DateTimeOptional(test)
//         val formatter: DateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
//        val testTime: OffsetDateTime = OffsetDateTime.parse(test, formatter)
//        println(testTime)

        printStringOptional(datetime)
        printStringOptional(date)
    }

    fun printStringOptional(dt: DateTimeOptional) {
        when (dt) {
            is SimpleDateTime -> {
                println("Hour: ${dt.dateTime.hour}")
            }

            is SimpleDate -> {
                println("Day of month: ${dt.date.dayOfMonth}")
            }

            else -> {
                println("unknown")
            }
        }
    }
}