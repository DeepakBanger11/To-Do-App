package com.getstarted.to_do_app_compose.helper

import android.content.Context
import androidx.compose.ui.platform.LocalContext

fun validateRegData(
    context: Context,
    email: String,
    password: String): Boolean{
    var validated = false
    if(email.isNotBlank() && password.isNotBlank()){
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            validated = true
        }
    }
    return validated
}


