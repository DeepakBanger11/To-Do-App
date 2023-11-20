package com.getstarted.to_do_app_compose.util


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ValidateSignUpTest{
    @Test
    fun `empty username returns false`(){
        val result = ValidateSignUp.validateSignUpData(
            "",
            "123@gmail.com",
            "123"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `empty email returns false`(){
        val result = ValidateSignUp.validateSignUpData(
            "123",
            "",
            "123"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `empty password returns false`(){
        val result = ValidateSignUp.validateSignUpData(
            "123",
            "123@gmail.com",
            ""
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `filled field returns true`(){
        val result = ValidateSignUp.validateSignUpData(
            "123",
            "123@gmail.com",
            "123"
        )
        assertThat(result).isTrue()
    }
}