@file:OptIn(ExperimentalMaterial3Api::class)

package com.getstarted.to_do_app_compose.ui.screens.list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.getstarted.to_do_app_compose.R
import com.getstarted.to_do_app_compose.ui.theme.BrandColorPrimary

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToTaskScreen:(taskId:Int) -> Unit
){
        Scaffold(
            topBar = {
                     ListAppBar()
            },
            content = { },
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
@Composable
@Preview
private fun ListScreenPreview()
{
    ListScreen(navigateToTaskScreen = {})
}