package dev.scratch.scheduler.util

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class DateTimeFrame(start: String, end: String) {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val startDateTime: OffsetDateTime = OffsetDateTime.parse(start, formatter)
    val endDateTime: OffsetDateTime = OffsetDateTime.parse(end, formatter)
}