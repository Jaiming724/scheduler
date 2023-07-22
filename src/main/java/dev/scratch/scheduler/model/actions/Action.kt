package dev.scratch.scheduler.model.actions

import java.time.DayOfWeek

abstract class Action constructor(
    val content: String,
    val days: Array<DayOfWeek>,
    val intervalBefore: Int = 15,
    val intervalAfter: Int = 15
) {




}