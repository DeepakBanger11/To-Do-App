package com.getstarted.to_do_app_compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.getstarted.to_do_app_compose.navigation.destinations.listComposable
import com.getstarted.to_do_app_compose.navigation.destinations.taskComposable
import com.getstarted.to_do_app_compose.util.Constants.LIST_SCREEN

@Composable
fun SetUpNavigation(
    navController: NavHostController
){
    val screen = remember(navController){
        Screens(navController = navController)
    }
    NavHost(navController = navController,
//        startDestination = LIST_SCREEN
            startDestination = LIST_SCREEN
    ){
        listComposable(navigateToTaskScreen = screen.task)
        taskComposable(navigateToListScreen = screen.list)
    }
}
