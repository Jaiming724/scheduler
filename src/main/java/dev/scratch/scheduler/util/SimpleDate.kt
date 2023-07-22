package dev.scratch.scheduler.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SimpleDate(
    str: String, private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
) : DateTimeOptional {
    val date = LocalDate.parse(str, dateFormatter)
    override fun toString(): String {
        return date.toString()
    }

}