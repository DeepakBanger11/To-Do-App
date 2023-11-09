

package com.getstarted.to_do_app_compose.ui.screens.list

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.getstarted.to_do_app_compose.R
import com.getstarted.to_do_app_compose.ui.viewmodal.SharedViewModal
import com.getstarted.to_do_app_compose.util.Action
import com.getstarted.to_do_app_compose.util.SearchAppBarState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen(
    action: Action,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModal: SharedViewModal,
    navController:NavController
) {

    LaunchedEffect(key1 = action){
        sharedViewModal.handleDatabaseActions(action = action)
    }


    //this collect all tasks in data base and also update it whenever there is change in database
    val allTasks by sharedViewModal.allTasks.collectAsState()
    val searchTasks by sharedViewModal.searchedTasks.collectAsState()
    val sortState by sharedViewModal.sortState.collectAsState()
    val lowPriorityTasks by sharedViewModal.lowPriorityTasks.collectAsState()
    val highPriorityTasks by sharedViewModal.highPriorityTasks.collectAsState()
//    for (task in allTasks.value){
//        Log.d("ListScreen",task.title)
//    }
    val searchAppBarState: SearchAppBarState by sharedViewModal.searchAppBarState
    val searchTextState: String by sharedViewModal.searchTextState

    val scaffoldState = remember { SnackbarHostState() }

    DisplaySnackBar(
        scaffoldState = scaffoldState,
        onComplete = { sharedViewModal.action.value = it },
        onUndoClicked = {
            sharedViewModal.action.value = it
        },
        taskTitle = sharedViewModal.title.value,
        action = action
    )

    Scaffold(
        snackbarHost = { SnackbarHost(scaffoldState) },
        topBar = {
            ListAppBar(
                sharedViewModal = sharedViewModal,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState,
                navController = navController
            )
        },
        content = {
            ListContent(
                allTasks = allTasks,
                searchTasks = searchTasks,
                lowPriorityTasks = lowPriorityTasks,
                highPriorityTasks = highPriorityTasks,
                sortState = sortState,
                searchAppBarState = searchAppBarState,
                onSwipeToDelete ={ action,task ->
                    sharedViewModal.action.value = action
                    sharedViewModal.updateTaskField(selectedTask = task)
                    scaffoldState.currentSnackbarData?.dismiss()
                },
                navigateToTaskScreen = navigateToTaskScreen
            )
        },
        floatingActionButton = {
            ListFab(onFabClicked = navigateToTaskScreen)
        }
    )
}

@Composable
fun ListFab(
    onFabClicked: (taskId: Int) -> Unit
) {
    FloatingActionButton(onClick = {
        onFabClicked(-1)
    }, containerColor = MaterialTheme.colorScheme.primary) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_button),
            tint = Color.White,
            modifier = Modifier.background(MaterialTheme.colorScheme.primary)
        )

    }
}

@Composable
fun DisplaySnackBar(
    scaffoldState: SnackbarHostState,
    onComplete: (Action) -> Unit,
    onUndoClicked: (Action) -> Unit,
    taskTitle: String,
    action: Action
) {


    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            scope.launch {
                val snackBarResult = scaffoldState.showSnackbar(
                    message = setMessage(action = action, taskTitle = taskTitle),
                    actionLabel = setActionLabel(action = action)
                )
                undoDeletedTask(
                    action = action,
                    snackBarResult = snackBarResult,
                    onUndoClicked = onUndoClicked
                )
            }
            onComplete(Action.NO_ACTION)
        }
        delay(2000)
        scaffoldState.currentSnackbarData?.dismiss()
    }
}

private fun setMessage(action: Action, taskTitle: String): String {
    return when (action) {
        Action.DELETE_ALL -> "All Tasks Removed."
        else -> "${action.name} : $taskTitle"
    }
}

private fun setActionLabel(action: Action): String {
    return if (action.name == "DELETE") {
        "UNDO"
    } else {
        "OK"
    }
}

private fun undoDeletedTask(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit
) {
    if (snackBarResult == SnackbarResult.ActionPerformed
        && action == Action.DELETE
    ) {
        onUndoClicked(Action.UNDO)
    }
}
