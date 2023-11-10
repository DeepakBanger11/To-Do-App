package com.getstarted.to_do_app_compose.helper

import android.content.Context
import androidx.compose.runtime.remember
import androidx.core.util.PatternsCompat
import com.getstarted.to_do_app_compose.repositories.PreferencesManager

class ValidateInput() {
    fun validateLogin(
        context: Context,
        userName:String,
        password:String
    ):Boolean
    {
        val sharedPreferences = context.getSharedPreferences("todo",Context.MODE_PRIVATE)
        var storedUserName = sharedPreferences.getString("username","")
        var storedpassword = sharedPreferences.getString("password","")
        var validated = false
        if( password.isNotBlank()&& userName.isNotBlank()){
            if (storedUserName == userName && storedpassword == password)
            {
                validated = true
            }
        }
        return validated
    }
    fun validateLoginEntry(
        context: Context,
        userName:String,
        password:String
    ):Boolean
    {
        val sharedPreferences = context.getSharedPreferences("todo",Context.MODE_PRIVATE)
        var storedUserName = sharedPreferences.getString("username","")
        var storedpassword = sharedPreferences.getString("password","")
        var validated = false
        if( password.isNotBlank()&& userName.isNotBlank()){
            if (storedUserName == userName || storedpassword == password)
            {
                validated = true
            }
        }
        return validated
    }
}