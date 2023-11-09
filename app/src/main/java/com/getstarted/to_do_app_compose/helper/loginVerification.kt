package com.getstarted.to_do_app_compose.helper

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.getstarted.to_do_app_compose.repositories.PreferencesManager



fun validateSignUpData(
    userName:String,
    email: String,
    password: String): Boolean{
    var validated = false
    if(email.isNotBlank() && password.isNotBlank()&& userName.isNotBlank()){
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            validated = true
        }
    }
    return validated
}

