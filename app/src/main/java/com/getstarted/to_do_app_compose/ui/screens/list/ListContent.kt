package com.getstarted.to_do_app_compose.ui.screens.list

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.getstarted.to_do_app_compose.R
import com.getstarted.to_do_app_compose.dataClasses.Priority
import com.getstarted.to_do_app_compose.dataClasses.ToDoTask
import com.getstarted.to_do_app_compose.ui.theme.HighPriorityColor
import com.getstarted.to_do_app_compose.ui.theme.LARGEST_PADDING
import com.getstarted.to_do_app_compose.ui.theme.LARGE_PADDING
import com.getstarted.to_do_app_compose.ui.theme.PRIORITY_INDICATOR_SIZE
import com.getstarted.to_do_app_compose.util.Action
import com.getstarted.to_do_app_compose.util.RequestState
import com.getstarted.to_do_app_compose.util.SearchAppBarState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ListContent(
    allTasks: RequestState<List<ToDoTask>>,
    searchTasks: RequestState<List<ToDoTask>>,
    lowPriorityTasks: List<ToDoTask>,
    highPriorityTasks: List<ToDoTask>,
    sortState: RequestState<Priority>,
    searchAppBarState: SearchAppBarState,
    onSwipeToDelete:(Action, ToDoTask) -> Unit,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    if (sortState is RequestState.Success) {
        when {
            searchAppBarState == SearchAppBarState.TRIGGERED -> {
                if (searchTasks is RequestState.Success) {
                    HandleListContent(
                        tasks = searchTasks.data,
                        onSwipeToDelete= onSwipeToDelete,
                        navigateToTaskScreen = navigateToTaskScreen
                    )
                }
            }

            sortState.data == Priority.NONE -> {
                if (allTasks is RequestState.Success) {
                    HandleListContent(
                        tasks = allTasks.data,
                        onSwipeToDelete= onSwipeToDelete,
                        navigateToTaskScreen = navigateToTaskScreen
                    )
                }
            }
            sortState.data == Priority.LOW ->{
                HandleListContent(
                    tasks = lowPriorityTasks,
                    onSwipeToDelete= onSwipeToDelete,
                    navigateToTaskScreen = navigateToTaskScreen
                )
            }
            sortState.data == Priority.HIGH ->{
                HandleListContent(
                    tasks = highPriorityTasks,
                    onSwipeToDelete= onSwipeToDelete,
                    navigateToTaskScreen = navigateToTaskScreen
                )
            }

        }

    }


}

@Composable
fun HandleListContent(
    tasks: List<ToDoTask>,
    onSwipeToDelete:(Action, ToDoTask) -> Unit,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    if (tasks.isEmpty()) {
        EmptyContent()
    } else {
        DisplayTasks(
            tasks = tasks,
            onSwipeToDelete = onSwipeToDelete,
            navigateToTaskScreen = navigateToTaskScreen
        )
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayTasks(
    tasks: List<ToDoTask>,
    onSwipeToDelete:(Action, ToDoTask) -> Unit,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 60.dp)
    ) {
        items(
            items = tasks,
            key = { task ->
                task.id
            }
        ) { task ->
            val dismissState = rememberDismissState()
            val dismissDirection  =  dismissState.dismissDirection
            val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)
            if (isDismissed && dismissDirection == DismissDirection.EndToStart){
                val scope = rememberCoroutineScope()
                scope.launch {
                    delay(300)
                    onSwipeToDelete(Action.DELETE,task)
                }
            }
            val degrees by animateFloatAsState(
                targetValue = if (dismissState.targetValue == DismissValue.Default)
                    0f
                else
                    -45f
            )
            var itemAppeared by remember { mutableStateOf(false) }
            LaunchedEffect(key1 = true){
                itemAppeared = true
            }
          AnimatedVisibility(
              visible = itemAppeared && !isDismissed,
              enter = expandVertically(
                  animationSpec = tween(
                      durationMillis = 300
                  )
              ),
              exit = shrinkHorizontally(
                  animationSpec = tween(
                      durationMillis = 300
                  )
              )
          ) {
              SwipeToDismiss(
                  state = dismissState,
                  directions = setOf(DismissDirection.EndToStart),
                  background = { RedBackground(degrees = degrees)},
                  dismissContent = {
                      TaskItem(
                          toDoTask = task,
                          navigateToTaskScreen = navigateToTaskScreen
                      )
                  })
          }

        }
    }

}

@Composable
fun RedBackground(degrees:Float)
{
    Box(modifier = Modifier
        .fillMaxSize()
        .background(HighPriorityColor)
        .padding(horizontal = LARGEST_PADDING),
        contentAlignment = Alignment.CenterEnd
//        .align(Alignment.CenterEnd)
        ){
        Icon(
            modifier = Modifier
                .rotate(degrees = degrees)
                .fillMaxSize(.5f),
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_icon),
            tint = Color.White
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Color.White,
        shape = RectangleShape,
        tonalElevation = 2.dp,
        shadowElevation = 2.dp,
        onClick = {
            navigateToTaskScreen(toDoTask.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(all = LARGE_PADDING)
                .fillMaxWidth()

        ) {
            Row {
                Text(
                    text = toDoTask.title,
                    color = Color.Gray,
                    style = TextStyle(
                        fontStyle = FontStyle(R.font.roboto_regular),
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    ),
                    modifier = Modifier.weight(8f),
                    maxLines = 1
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(
                        modifier = Modifier
                            .width(PRIORITY_INDICATOR_SIZE)
                            .height(PRIORITY_INDICATOR_SIZE)
                    )
                    {
                        drawCircle(color = toDoTask.priority.color)
                    }
                }

            }

            Text(
                text = toDoTask.description,
                modifier = Modifier
                    .fillMaxWidth(),
                color = Color.Gray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}

@Composable
@Preview
fun TaskItemPreview() {
    TaskItem(toDoTask = ToDoTask(
        id = 0,
        title = "title",
        description = " something is here, you will achieve it",
        priority = Priority.MEDIUM
    ), navigateToTaskScreen = {})
}
