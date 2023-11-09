package com.getstarted.to_do_app_compose.navigation

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.getstarted.to_do_app_compose.navigation.destinations.listComposable
import com.getstarted.to_do_app_compose.navigation.destinations.loginComposable
import com.getstarted.to_do_app_compose.navigation.destinations.signupComposable
import com.getstarted.to_do_app_compose.navigation.destinations.splashComposable
import com.getstarted.to_do_app_compose.navigation.destinations.taskComposable
import com.getstarted.to_do_app_compose.ui.viewmodal.SharedViewModal
import com.getstarted.to_do_app_compose.util.Constants.SPLASH_SCREEN

@Composable
fun SetUpNavigation(
    context: Context,
    navController: NavHostController,
    sharedViewModal: SharedViewModal,
) {
    val screen = remember(navController) {
        Screens(
            context = context,
            navController = navController
        )
    }
    NavHost(
        navController = navController,
        //startDestination = LOGIN_SCREEN
        startDestination = SPLASH_SCREEN
    ) {
        val sharedPreferences = context.getSharedPreferences("todo", Context.MODE_PRIVATE)
        var page = sharedPreferences.getString("whichPage", "")
        Log.d("navi", "$page")

        splashComposable(
            navigateToLoginScreen = screen.splash,
            navigateToListScreen = screen.task
        )
        loginComposable(
            navigateToListScreen = screen.task,
            navController = navController
        )
        listComposable(
            navigateToTaskScreen = screen.list,
            sharedViewModal = sharedViewModal,
            navController = navController
        )
        taskComposable(
            sharedViewModal = sharedViewModal,
            navigateToListScreen = screen.task
        )
        signupComposable(
            navigateToListScreen = screen.task
        )

    }
}





