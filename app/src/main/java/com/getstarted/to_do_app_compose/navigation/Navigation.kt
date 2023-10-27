package com.getstarted.to_do_app_compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.getstarted.to_do_app_compose.ui.viewmodal.SharedViewModal
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.getstarted.to_do_app_compose.navigation.destinations.listComposable
import com.getstarted.to_do_app_compose.navigation.destinations.taskComposable
import com.getstarted.to_do_app_compose.util.Constants.LIST_SCREEN
import com.getstarted.to_do_app_compose.util.Constants.LOGIN_SCREEN
//
//@Composable
//fun SetUpNavigation(
//    navController: NavHostController,
//    sharedViewModal: SharedViewModal
//) {
//    val screen = remember(navController) {
//        Screens(navController = navController)
//    }
//    NavHost(
//        navController = navController,
//        startDestination = LIST_SCREEN
////        startDestination = LOGIN_SCREEN
//    ) {
//        listComposable(
//            navigateToTaskScreen = screen.task,
//            sharedViewModal = sharedViewModal
//        )
//        taskComposable(navigateToListScreen = screen.list)
////        loginComposable(navigateToListScreen = screen.list)
//    }
//
//}


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
        startDestination = LIST_SCREEN
    ) {
        listComposable(
            navigateToTaskScreen = screen.list,
            sharedViewModal = sharedViewModal
        )
        taskComposable(
            navigateToListScreen = screen.task
        )
    }
}
