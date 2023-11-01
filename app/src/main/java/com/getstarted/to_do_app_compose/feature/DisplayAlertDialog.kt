package com.getstarted.to_do_app_compose.feature


import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.getstarted.to_do_app_compose.R

@Composable
fun DisplayAlertDialog(
    title:String,
    message:String,
    openDialog:Boolean,
    closeDialog:() -> Unit,
    onYesClicked:() ->Unit
){
    if(openDialog){
        AlertDialog(
            title = {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(text = message,
                    fontWeight = FontWeight.Normal)
            },
            onDismissRequest = {
                closeDialog()
            },
            confirmButton = {
                Button(
                    onClick = {
                        onYesClicked()
                        closeDialog()
                    }
                ) {
                    Text(stringResource(id = R.string.yes))
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        closeDialog()
                    }
                ) {
                    Text(stringResource(id = R.string.no))
                }
            }
        )
    }
}