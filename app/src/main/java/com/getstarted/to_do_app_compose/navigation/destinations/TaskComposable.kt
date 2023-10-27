package com.getstarted.to_do_app_compose.navigation.destinations


import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.getstarted.to_do_app_compose.util.Action
import com.getstarted.to_do_app_compose.util.Constants.TASK_ARGUMENT_KEY
import com.getstarted.to_do_app_compose.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreen:(Action) -> Unit
){
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY){
            type = NavType.StringType
        })
    ){navBackStackEntry ->
    val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        Log.d("TaskComposable",taskId.toString())

    }
}