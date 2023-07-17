package dev.scratch.scheduler.model

import dev.scratch.scheduler.model.actions.Action
import dev.scratch.scheduler.model.actions.HardAction
import dev.scratch.scheduler.util.TimeFrame
import java.util.*

class Schedule {
    val schedule = TreeMap<TimeFrame, Action>()

    fun addHardAction(action: HardAction) {
        schedule[action.timeFrame] = action
    }

    fun addConstraint(aBreak: Break) {
        schedule[aBreak.timeFrame] = aBreak
    }

}