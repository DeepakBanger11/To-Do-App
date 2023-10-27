@file:OptIn(ExperimentalMaterial3Api::class)

package com.getstarted.to_do_app_compose.ui.screens.list

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.getstarted.to_do_app_compose.R
import com.getstarted.to_do_app_compose.ui.theme.BrandColorPrimary
import com.getstarted.to_do_app_compose.ui.viewmodal.SharedViewModal
import com.getstarted.to_do_app_compose.util.SearchAppBarState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToTaskScreen:(taskId:Int) -> Unit,
    sharedViewModal: SharedViewModal
){
    LaunchedEffect(key1 = true){
        Log.d("ListScreen","LaunchEffect Triggered!")
        sharedViewModal.getAllTasks()
    }
    //this collect all tasks in data base and also update it whenever there is change in database
    val allTasks by sharedViewModal.allTasks.collectAsState()
//    for (task in allTasks.value){
//        Log.d("ListScreen",task.title)
//    }
    val searchAppBarState:SearchAppBarState by sharedViewModal.searchAppBarState
    val searchTextState:String by sharedViewModal.searchTextState
        Scaffold(
            topBar = {
                     ListAppBar(
                         sharedViewModal = sharedViewModal,
                         searchAppBarState = searchAppBarState,
                         searchTextState = searchTextState
                     )
            },
            content = {
                ListContent(
                tasks = allTasks,
                navigateToTaskScreen = navigateToTaskScreen
            )},
            floatingActionButton = {
                ListFab(onFabClicked = navigateToTaskScreen)
            }
        )
}
@Composable
fun ListFab(
    onFabClicked:(taskId:Int) -> Unit
){
    FloatingActionButton(onClick = {
        onFabClicked(-1)
    }, containerColor = BrandColorPrimary) {
        Icon(imageVector = Icons.Filled.Add ,
            contentDescription = stringResource(id = R.string.add_button),
            tint = Color.White,
            modifier = Modifier.background(BrandColorPrimary)
        )
        
    }
}
//@Composable
//@Preview
//private fun ListScreenPreview()
//{
//    ListScreen(navigateToTaskScreen = {},
//        sharedViewModal = {})
//}