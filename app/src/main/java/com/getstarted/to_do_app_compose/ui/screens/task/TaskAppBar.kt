package com.getstarted.to_do_app_compose.ui.screens.task

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.getstarted.to_do_app_compose.R
import com.getstarted.to_do_app_compose.dataClasses.Priority
import com.getstarted.to_do_app_compose.dataClasses.ToDoTask
import com.getstarted.to_do_app_compose.ui.theme.BrandColorPrimary
import com.getstarted.to_do_app_compose.util.Action

@Composable
fun TaskAppBar(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {
    if (selectedTask == null){
        NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    }
    else{
        ExistingTaskAppBar(navigateToListScreen = navigateToListScreen, selectedTask = selectedTask)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskAppBar(
    navigateToListScreen:(Action) -> Unit
){
    TopAppBar(title = {
        Text(text = stringResource(id = R.string.add_task),
            color = Color.White)
    },
        navigationIcon = {
            BackAction(onBackClicked = navigateToListScreen)
        },
        modifier = Modifier.background(BrandColorPrimary),
        colors = TopAppBarDefaults.smallTopAppBarColors(BrandColorPrimary),
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
fun NewTaskAppBarPreview()
{
    NewTaskAppBar(navigateToListScreen = {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExistingTaskAppBar(
    navigateToListScreen:(Action) -> Unit,
    selectedTask: ToDoTask
){
    TopAppBar(title = {
        Text(text = selectedTask.title,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis)
    },
        navigationIcon = {
                         CloseAction(onCloseClicked = navigateToListScreen)
        },
        modifier = Modifier.background(BrandColorPrimary),
        colors = TopAppBarDefaults.smallTopAppBarColors(BrandColorPrimary),
        actions = {
            DeleteAction(onDeleteClicked = navigateToListScreen)
            UpdateAction(onUpdateClicked = navigateToListScreen)
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
fun DeleteAction(
    onDeleteClicked: (Action) -> Unit
) {
    IconButton(onClick = { onDeleteClicked(Action.DELETE) }) {
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
fun ExistingTaskAppBarPreview()
{
    ExistingTaskAppBar(navigateToListScreen = {}, selectedTask = ToDoTask(
        id = 0,
        title = "Batgirl",
        description = "super hero , dcu , works along with batman",
        priority = Priority.MEDIUM
    )
    )
}