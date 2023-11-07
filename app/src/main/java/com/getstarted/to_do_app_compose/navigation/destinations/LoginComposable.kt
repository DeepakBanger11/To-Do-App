package com.getstarted.to_do_app_compose.navigation.destinations

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.getstarted.to_do_app_compose.feature.loginScreen
import com.getstarted.to_do_app_compose.util.Action
import com.getstarted.to_do_app_compose.util.Constants

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.loginComposable(
    navigateToListScreen: (Action) -> Unit
){
    composable(
        route = Constants.LOGIN_SCREEN,
        exitTransition = {
            slideOutVertically(
                animationSpec = tween(200),
                targetOffsetY = {fullHeight -> -fullHeight }
            )
        }
       )
    {
        val context = LocalContext.current
        loginScreen(
            context = context,
            navigateToListScreen = navigateToListScreen
        )
    }
}