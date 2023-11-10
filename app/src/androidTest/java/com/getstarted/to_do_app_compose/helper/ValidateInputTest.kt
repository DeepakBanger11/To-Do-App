package com.getstarted.to_do_app_compose.helper

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ValidateInputTest {
    private lateinit var validateInput: ValidateInput

    @Before
    fun setup() {
        validateInput = ValidateInput()
    }

    @Test
    fun validEntriesProvided_returnTrue() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = validateInput.validateLogin(context, "test", "test")
        assertThat(result).isTrue()
    }

    @Test
    fun validSingleEntryProvided_returnTrue() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = validateInput.validateLoginEntry(context, "test", "123")
        assertThat(result).isTrue()
    }
}