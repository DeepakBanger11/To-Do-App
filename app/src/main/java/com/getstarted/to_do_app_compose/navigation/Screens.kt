package com.getstarted.to_do_app_compose.navigation


import androidx.navigation.NavHostController
import com.getstarted.to_do_app_compose.util.Action
import com.getstarted.to_do_app_compose.util.Constants.LIST_SCREEN
import com.getstarted.to_do_app_compose.util.Constants.LOGIN_SCREEN
import com.getstarted.to_do_app_compose.util.Constants.SPLASH_SCREEN

class Screens(navController: NavHostController) {
    val splash: () -> Unit = {
        navController.navigate("login") {
            popUpTo(SPLASH_SCREEN) { inclusive = true }
        }
    }
    val login :() ->Unit ={
        navController.navigate("list/{action}") {
            popUpTo(LOGIN_SCREEN) { inclusive = true }
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


    //    val login:(Next) -> Unit ={next ->
//        navController.navigate("list/${next.name}"){
//            popUpTo(LOGIN_SCREEN){inclusive = true}
//        }
//
//    }
    val action: (Int) -> Unit = { action ->
        navController.navigate("list/{action}")
    }
}