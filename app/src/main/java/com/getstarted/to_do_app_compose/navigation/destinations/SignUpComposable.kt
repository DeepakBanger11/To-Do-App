package com.getstarted.to_do_app_compose.navigation.destinations

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.getstarted.to_do_app_compose.feature.loginScreen
import com.getstarted.to_do_app_compose.feature.signUp
import com.getstarted.to_do_app_compose.repositories.PreferencesManager
import com.getstarted.to_do_app_compose.util.Action
import com.getstarted.to_do_app_compose.util.Constants

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.signupComposable(
    navigateToListScreen: (Action) -> Unit
){
    composable(
        route = Constants.SIGNUP_SCREEN,
        exitTransition = {
            slideOutVertically(
                animationSpec = tween(200),
                targetOffsetY = {fullHeight -> -fullHeight }
            )
        }
    )
    {
        val context = LocalContext.current
        val preferencesManager = remember { PreferencesManager(context) }
        signUp(
            context = context,
            navigateToListScreen = navigateToListScreen
        )
    }
}