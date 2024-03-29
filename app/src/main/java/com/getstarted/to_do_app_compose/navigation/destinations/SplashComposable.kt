package com.getstarted.to_do_app_compose.navigation.destinations

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.getstarted.to_do_app_compose.ui.screens.splash.SplashScreen
import com.getstarted.to_do_app_compose.util.Action
import com.getstarted.to_do_app_compose.util.Constants

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.splashComposable(
    navigateToLoginScreen: () -> Unit,
    navigateToListScreen:(Action) -> Unit

){
    composable(
        route = Constants.SPLASH_SCREEN,
        exitTransition = {
            slideOutVertically(
                animationSpec = tween(200),
                targetOffsetY = {fullHeight -> -fullHeight }
            )
        }
        )
    {
        val context = LocalContext.current

        SplashScreen(context = context,
            navigateToLoginScreen = navigateToLoginScreen,
            navigateToListScreen = navigateToListScreen)

    }
}