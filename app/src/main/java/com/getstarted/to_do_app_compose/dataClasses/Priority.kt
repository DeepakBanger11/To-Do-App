package com.getstarted.to_do_app_compose.dataClasses

import androidx.compose.ui.graphics.Color
import com.getstarted.to_do_app_compose.ui.theme.HighPriorityColor
import com.getstarted.to_do_app_compose.ui.theme.LowPriorityColor
import com.getstarted.to_do_app_compose.ui.theme.MediumPriorityColor
import com.getstarted.to_do_app_compose.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}