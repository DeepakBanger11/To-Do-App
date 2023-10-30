package com.getstarted.to_do_app_compose.ui.screens.task

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.getstarted.to_do_app_compose.dataClasses.Priority
import com.getstarted.to_do_app_compose.dataClasses.ToDoTask
import com.getstarted.to_do_app_compose.ui.theme.LARGE_PADDING
import com.getstarted.to_do_app_compose.ui.viewmodal.SharedViewModal
import com.getstarted.to_do_app_compose.util.Action

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    sharedViewModel:SharedViewModal,
    navigateToListScreen: (Action) -> Unit
) {
    val title : String by sharedViewModel.title
    val description : String by sharedViewModel.description
    val priority : Priority by sharedViewModel.priority
    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = navigateToListScreen
            )
        },
        content = {
            TaskContent(
                modifier = Modifier.padding(it),
                title = title,
                onTitleChange = {
                                sharedViewModel.title.value = it
                },
                description = description,
                onDescriptionChange = {
                                      sharedViewModel.description.value = it
                },
                priority = priority,
                onPrioritySelected = {
                                     sharedViewModel.priority.value = it
                },
            )
        }
    )
}