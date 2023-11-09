package com.getstarted.to_do_app_compose.navigation


import android.content.Context
import android.util.Log
import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.getstarted.to_do_app_compose.util.Action
import com.getstarted.to_do_app_compose.util.Constants.LIST_SCREEN
import com.getstarted.to_do_app_compose.util.Constants.LOGIN_SCREEN
import com.getstarted.to_do_app_compose.util.Constants.SIGNUP_SCREEN
import com.getstarted.to_do_app_compose.util.Constants.SPLASH_SCREEN

class Screens(context: Context,
    navController: NavHostController
) {
    val sharedPreferences = context.getSharedPreferences("todo",Context.MODE_PRIVATE)
    var page = sharedPreferences.getString("whichPage","")
    val splash: () -> Unit = {
        navController.navigate(
            route =
                when (page) {
                    "signup" -> "signup"
                    "list/{action}" -> "list/{action}"
                    else -> "login"
                }
        ) {
            popUpTo(SPLASH_SCREEN) { inclusive = true }
        }
    }
    val login :() ->Unit ={
        navController.navigate(
            route =
            when (page) {
                "signup" -> "signup"
                else -> "list/{action}"
            }
        ) {
            popUpTo(LOGIN_SCREEN) { inclusive = true }
        }
    }
    val signup: () -> Unit = {
        navController.navigate("list/{action}") {
            popUpTo(SIGNUP_SCREEN) { inclusive = true }
        }
    }
    val list: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId")
    }
    val task: (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }
    }
    val action: (Int) -> Unit = { action ->
        navController.navigate("list/{action}")
    }
}