package dev.scratch.scheduler.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

sealed interface DateTimeOptional {
    companion object {

        operator fun invoke(string: String): DateTimeOptional {
            if (hasTime(string)) {
                return SimpleDateTime(string)
            } else {
                return SimpleDate(string)
            }
        }

        fun hasTime(datetimeString: String): Boolean {
            try {
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
                val dateTime = LocalDateTime.parse(datetimeString, formatter)
                return true
            } catch (e: DateTimeParseException) {
                return false
            }
        }

    }

}
