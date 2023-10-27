package com.getstarted.to_do_app_compose.feature

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.getstarted.to_do_app_compose.dataClasses.Priority
import com.getstarted.to_do_app_compose.ui.theme.PRIORITY_DROP_DOWN_HEIGHT
import com.getstarted.to_do_app_compose.ui.theme.PRIORITY_INDICATOR_SIZE
import com.getstarted.to_do_app_compose.R

@Composable
fun PriorityDropDown(
    priority: Priority,
    onPrioritySelected:(Priority) -> Unit
){
    var expanded by remember{
        mutableStateOf(false)
    }
    val angle: Float by animateFloatAsState(
        targetValue = if(expanded)180f else 0f)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(PRIORITY_DROP_DOWN_HEIGHT)
            .clickable { expanded = true }
            .border(
                BorderStroke(1.dp, color = Color.LightGray)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(modifier = Modifier
            .size(PRIORITY_INDICATOR_SIZE)
            .weight(1f)){
            drawCircle(color = priority.color)
        }
        Text(text = priority.name, modifier = Modifier.weight(8f))
        IconButton(onClick = { expanded = true},
            modifier = Modifier
                .alpha(1f)
                .rotate(angle)) {
            Icon(imageVector = Icons.Filled.ArrowDropDown, 
                contentDescription = stringResource(id = R.string.drop_down_arrow),
                modifier = Modifier.weight(1.5f))
            
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false },
            modifier = Modifier.fillMaxWidth()) {
            DropdownMenuItem(text = { PriorityItem(priority = Priority.LOW) },
                onClick = { expanded = false
                onPrioritySelected(Priority.LOW)})
            DropdownMenuItem(text = { PriorityItem(priority = Priority.MEDIUM) },
                onClick = { expanded = false
                    onPrioritySelected(Priority.MEDIUM)})
            DropdownMenuItem(text = { PriorityItem(priority = Priority.HIGH) },
                onClick = { expanded = false
                    onPrioritySelected(Priority.HIGH)})
            DropdownMenuItem(text = { PriorityItem(priority = Priority.NONE) },
                onClick = { expanded = false
                    onPrioritySelected(Priority.NONE)})
        }
    }
}

@Composable
@Preview
fun PriorityDropDownPreview()
{
   PriorityDropDown(priority = Priority.MEDIUM , onPrioritySelected = {})
}