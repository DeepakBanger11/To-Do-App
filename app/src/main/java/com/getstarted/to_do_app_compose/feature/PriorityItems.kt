package com.getstarted.to_do_app_compose.feature

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.getstarted.to_do_app_compose.dataClasses.Priority
import com.getstarted.to_do_app_compose.ui.theme.LARGE_PADDING
import com.getstarted.to_do_app_compose.ui.theme.PRIORITY_INDICATOR_SIZE

@Composable
fun PriorityItem(priority: Priority)
{
    Row(
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Canvas(modifier = Modifier.size(PRIORITY_INDICATOR_SIZE))
        {
            drawCircle(color = priority.color)
        }
        Text(   text = priority.name,
                modifier = Modifier.padding(LARGE_PADDING),
//              style = Typography.section,
                color = Color.Black
        )
    }
}

@Composable
@Preview
fun PriorityItemsPreview()
{
    PriorityItem(priority = Priority.HIGH)
}