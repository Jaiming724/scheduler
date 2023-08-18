package dev.scratch.scheduler.service

import dev.scratch.scheduler.model.Break
import dev.scratch.scheduler.model.Schedule
import dev.scratch.scheduler.model.actions.Action
import dev.scratch.scheduler.model.actions.HardAction
import dev.scratch.scheduler.model.actions.SoftAction
import java.time.DayOfWeek
import java.util.*

class Scheduler {
    private val softActions = mutableListOf<SoftAction>()
    private val hardAction = mutableListOf<HardAction>()
    private val breaks = mutableListOf<Break>()
    private val schedules = TreeMap<DayOfWeek, Schedule>()

    init {
        for (day in DayOfWeek.values()) {
            schedules[day] = Schedule()
        }
        println(schedules.map { "${it.key}: ${it.value}" }.joinToString(", "))

    }

    fun addAction(action: Action) {
        if (action is SoftAction) {
            softActions.add(action)
        } else {
            hardAction.add(action as HardAction)
        }
    }

    fun addConstraint(aBreak: Break) {
        breaks.add(aBreak)
    }

    fun schedule(): Map<DayOfWeek, Schedule> {
        for (action in hardAction) {
            for (day in action.days) {
                schedules[day]!!.addHardAction(action)
            }
        }
        for (breakItem in breaks) {
            for (day in breakItem.days) {
                schedules[day]!!.addConstraint(breakItem)
            }
        }

        return schedules
    }

    fun clear() {
        softActions.clear()
        hardAction.clear()
        breaks.clear()
        schedules.clear()
    }

}