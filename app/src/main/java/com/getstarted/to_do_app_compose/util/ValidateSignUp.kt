package com.getstarted.to_do_app_compose.util

import androidx.core.util.PatternsCompat

object ValidateSignUp {

    fun validateSignUpData(
        userName:String,
        email: String,
        password: String): Boolean{
        var validated = false
        if(email.isNotBlank() && password.isNotBlank()&& userName.isNotBlank()){
            if (PatternsCompat.EMAIL_ADDRESS.matcher(email).matches())
            {
                validated = true
            }
        }
        return validated
    }
}