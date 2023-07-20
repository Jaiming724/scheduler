package dev.scratch.scheduler.util

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class SimpleDateTime(
    date: String,
    private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
) : DateTimeOptional {
    val dateTime = OffsetDateTime.parse(date, formatter)
}
