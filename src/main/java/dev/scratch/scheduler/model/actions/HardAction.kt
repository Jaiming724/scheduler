package dev.scratch.scheduler.model.actions

import dev.scratch.scheduler.util.TimeFrame
import java.time.DayOfWeek

open class HardAction(
    content: String, val timeFrame: TimeFrame, days: Array<DayOfWeek>, intervalBefore: Int = 15, intervalAfter: Int = 15
) : Action(content, days, intervalBefore, intervalAfter) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HardAction

        if (timeFrame != other.timeFrame || content != other.content)
            return false

        return true
    }

    override fun hashCode(): Int {
        return timeFrame.hashCode() + content.hashCode()
    }


}