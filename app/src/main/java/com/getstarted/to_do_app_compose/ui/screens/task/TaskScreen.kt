package com.getstarted.to_do_app_compose.ui.screens.task

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.getstarted.to_do_app_compose.dataClasses.ToDoTask
import com.getstarted.to_do_app_compose.util.Action

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    navigateToListScreen:(Action) -> Unit
){
    Scaffold(
      topBar ={
              TaskAppBar(selectedTask =selectedTask,
                  navigateToListScreen = navigateToListScreen)
      },
        content = {}
    )
}