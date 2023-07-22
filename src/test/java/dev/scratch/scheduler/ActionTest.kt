package dev.scratch.scheduler

import dev.scratch.scheduler.model.Break
import dev.scratch.scheduler.model.actions.HardAction
import dev.scratch.scheduler.model.actions.SoftAction
import dev.scratch.scheduler.service.Scheduler
import dev.scratch.scheduler.util.TimeFrame
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test
import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalTime

class ActionTest {
    val localTime1 = LocalTime.of(10, 0, 0)
    val localTime2 = LocalTime.of(10, 30, 0)
    val localTime3 = LocalTime.of(11, 0, 0)
    val localTime4 = LocalTime.of(12, 30, 0)
    val timeFrame1 = TimeFrame(localTime1, localTime2)
    val timeFrame2 = TimeFrame(localTime3, localTime4)
    val timeFrame3 = TimeFrame(LocalTime.MIDNIGHT, LocalTime.of(10, 0, 0))
    val timeFrame4 = TimeFrame(LocalTime.of(15, 0, 0), LocalTime.of(23, 59, 59))
    val scheduleService = Scheduler()

    val hardAction0 = HardAction("Lunch", timeFrame2, arrayOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY), 0, 0)
    val hardAction1 = HardAction("Lunch", timeFrame2, arrayOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY), 0, 0)
    val hardAction2 = HardAction("Morning Class", timeFrame1, arrayOf(DayOfWeek.MONDAY), 0, 0)
    val hardAction3 = HardAction("Sleep", timeFrame3, arrayOf(DayOfWeek.MONDAY), 0, 0)
    val constraint2 = Break("Break", timeFrame4, arrayOf(DayOfWeek.MONDAY))

    fun main(args: Array<String>) {

        scheduleService.addAction(hardAction1)
        scheduleService.addAction(hardAction2)
        scheduleService.addAction(hardAction3)
        scheduleService.addConstraint(constraint2)
        scheduleService.addAction(
            SoftAction(
                Duration.ofMinutes(30),
                "Task 1",
                arrayOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY),
                0,
                0
            )
        )
        scheduleService.addAction(SoftAction(Duration.ofMinutes(45), "Task 2", arrayOf(DayOfWeek.MONDAY), 0, 30))
        scheduleService.addAction(SoftAction(Duration.ofMinutes(45), "Task 3", arrayOf(DayOfWeek.MONDAY), 15, 30))
        scheduleService.schedule().forEach { it ->
            println(it.key)
            it.value.schedule.forEach {
                println(it.value.content)
            }
            println()
        }
    }

    @Test
    fun hardActionTest() {
        assertTrue(hardAction0==hardAction1)
        assertFalse(hardAction0== hardAction2)
    }
}