package com.getstarted.to_do_app_compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.getstarted.to_do_app_compose.navigation.destinations.listComposable
import com.getstarted.to_do_app_compose.navigation.destinations.loginComposable
import com.getstarted.to_do_app_compose.navigation.destinations.splashComposable
import com.getstarted.to_do_app_compose.navigation.destinations.taskComposable
import com.getstarted.to_do_app_compose.ui.viewmodal.SharedViewModal
import com.getstarted.to_do_app_compose.util.Constants.LIST_SCREEN
import com.getstarted.to_do_app_compose.util.Constants.LOGIN_SCREEN
import com.getstarted.to_do_app_compose.util.Constants.SPLASH_SCREEN

@Composable
fun SetUpNavigation(
    navController: NavHostController,
    sharedViewModal: SharedViewModal
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }
    NavHost(
        navController = navController,
        //startDestination = LOGIN_SCREEN
        startDestination = SPLASH_SCREEN
    ) {
        splashComposable(
            navigateToLoginScreen = screen.splash
        )
        loginComposable(navigateToListScreen = screen.task)
        listComposable(
            navigateToTaskScreen = screen.list,
            sharedViewModal = sharedViewModal
        )
        taskComposable(
            sharedViewModal = sharedViewModal,
            navigateToListScreen = screen.task
        )

    }
}
