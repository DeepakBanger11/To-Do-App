package com.getstarted.to_do_app_compose.helper

fun validateRegData(email: String,password:String): Boolean{
    var validated = false
    if(email.isNotBlank() && password.isNotBlank()){
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {validated = true}
    }
    println(validated)
    return validated
}