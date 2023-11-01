package com.getstarted.to_do_app_compose.ui.screens.task


import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.getstarted.to_do_app_compose.R
import com.getstarted.to_do_app_compose.dataClasses.Priority
import com.getstarted.to_do_app_compose.dataClasses.ToDoTask
import com.getstarted.to_do_app_compose.feature.DisplayAlertDialog
import com.getstarted.to_do_app_compose.util.Action

@Composable
fun TaskAppBar(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {
    if (selectedTask == null) {
        NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    } else {
        ExistingTaskAppBar(navigateToListScreen = navigateToListScreen, selectedTask = selectedTask)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskAppBar(
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(title = {
        Text(
            text = stringResource(id = R.string.add_task),
            color = Color.White
        )
    },
        navigationIcon = {
            BackAction(onBackClicked = navigateToListScreen)
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.primary),
        colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primary),
        actions = {
            AddAction(onAddClicked = navigateToListScreen)
        }

    )
}

@Composable
fun BackAction(
    onBackClicked: (Action) -> Unit
) {
    IconButton(onClick = { onBackClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.back_arrow),
            tint = Color.White
        )
    }
}

@Composable
fun AddAction(
    onAddClicked: (Action) -> Unit
) {
    IconButton(onClick = { onAddClicked(Action.ADD) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.add_task),
            tint = Color.White
        )
    }
}

@Composable
@Preview
fun NewTaskAppBarPreview() {
    NewTaskAppBar(navigateToListScreen = {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExistingTaskAppBar(
    navigateToListScreen: (Action) -> Unit,
    selectedTask: ToDoTask
) {
    TopAppBar(title = {
        Text(
            text = selectedTask.title,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    },
        navigationIcon = {
            CloseAction(onCloseClicked = navigateToListScreen)
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.primary),
        colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primary),
        actions = {
            ExistingTaskAppBarActions(navigateToListScreen = navigateToListScreen, selectedTask = selectedTask)
        }

    )
}

@Composable
fun CloseAction(
    onCloseClicked: (Action) -> Unit
) {
    IconButton(onClick = { onCloseClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(id = R.string.close_icon),
            tint = Color.White
        )
    }
}

@Composable
fun ExistingTaskAppBarActions(
    navigateToListScreen: (Action) -> Unit,
    selectedTask: ToDoTask
){
    var openDialog by remember{ mutableStateOf(false) }

    DisplayAlertDialog(
        title = stringResource(id = R.string.delete_task,selectedTask.title),
        message = stringResource(id = R.string.delete_task_confirmation,selectedTask.title) ,
        openDialog = openDialog,
        closeDialog = { openDialog = false},
        onYesClicked = {navigateToListScreen(Action.DELETE)}
    )
    DeleteAction(onDeleteClicked = {
        openDialog = true
    })
    UpdateAction(onUpdateClicked = navigateToListScreen)
}

@Composable
fun DeleteAction(
    onDeleteClicked: () -> Unit
) {
    IconButton(onClick = { onDeleteClicked() }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_icon),
            tint = Color.White
        )
    }
}

@Composable
fun UpdateAction(
    onUpdateClicked: (Action) -> Unit
) {
    IconButton(onClick = { onUpdateClicked(Action.UPDATE) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.update_icon),
            tint = Color.White
        )
    }
}

@Composable
@Preview
fun ExistingTaskAppBarPreview() {
    ExistingTaskAppBar(
        navigateToListScreen = {}, selectedTask = ToDoTask(
            id = 0,
            title = "Batgirl",
            description = "super hero , dcu , works along with batman",
            priority = Priority.MEDIUM
        )
    )
}