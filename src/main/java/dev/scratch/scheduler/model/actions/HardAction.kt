package dev.scratch.scheduler.model.actions

import dev.scratch.scheduler.util.TimeFrame
import java.time.DayOfWeek

open class HardAction(
    content: String, val timeFrame: TimeFrame, days: Array<DayOfWeek>, intervalBefore: Int = 15, intervalAfter: Int = 15
) : Action(content, days, intervalBefore, intervalAfter) {

}