package dev.scratch.scheduler.model

import dev.scratch.scheduler.model.actions.HardAction
import dev.scratch.scheduler.util.TimeFrame
import java.time.DayOfWeek

class Break(content: String, timeFrame: TimeFrame, days: Array<DayOfWeek>) :HardAction(content, timeFrame, days,0,0)