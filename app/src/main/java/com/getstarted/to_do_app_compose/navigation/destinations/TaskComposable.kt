package com.getstarted.to_do_app_compose.navigation.destinations


import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.getstarted.to_do_app_compose.ui.screens.task.TaskScreen
import com.getstarted.to_do_app_compose.ui.viewmodal.SharedViewModal
import com.getstarted.to_do_app_compose.util.Action
import com.getstarted.to_do_app_compose.util.Constants.TASK_ARGUMENT_KEY
import com.getstarted.to_do_app_compose.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    sharedViewModal: SharedViewModal,
    navigateToListScreen:(Action) -> Unit
){
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY){
            type = NavType.IntType
        }),
        enterTransition = {
            slideInHorizontally(
                animationSpec = tween(600),
                initialOffsetX = {fullWidth ->-fullWidth }
            )
        }
    ){navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY,0)
        LaunchedEffect(key1 = taskId){
            sharedViewModal.getSelectedTask(taskId = taskId)
        }

        val selectedTask by sharedViewModal.selectedTask.collectAsState()
        LaunchedEffect(key1 = selectedTask){
            if (selectedTask != null || taskId == -1){
                sharedViewModal.updateTaskField(selectedTask =selectedTask)
            }

        }
        TaskScreen(
            selectedTask = selectedTask,
            sharedViewModel = sharedViewModal,
            navigateToListScreen = navigateToListScreen
        )
    }
}