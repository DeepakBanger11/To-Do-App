package com.getstarted.to_do_app_compose.navigation


import androidx.navigation.NavHostController
import com.getstarted.to_do_app_compose.util.Action
import com.getstarted.to_do_app_compose.util.Constants.LIST_SCREEN

class Screens(navController: NavHostController) {
    val list: (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }

    }
    val task: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId")
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