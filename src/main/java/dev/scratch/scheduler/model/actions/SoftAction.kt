package dev.scratch.scheduler.model.actions

import java.time.DayOfWeek
import java.time.Duration

class SoftAction(
    val duration: Duration,
    content: String,
    dayOfWeek: Array<DayOfWeek>,
    intervalBefore: Int = 15,
    intervalAfter: Int = 15
) : Action(content, dayOfWeek, intervalBefore, intervalAfter)