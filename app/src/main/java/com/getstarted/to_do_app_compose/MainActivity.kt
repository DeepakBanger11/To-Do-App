@file:OptIn(ExperimentalMaterial3Api::class)

package com.getstarted.to_do_app_compose

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.getstarted.to_do_app_compose.navigation.SetUpNavigation
import com.getstarted.to_do_app_compose.repositories.PreferencesManager
import com.getstarted.to_do_app_compose.ui.theme.ToDoAppComposeTheme
import com.getstarted.to_do_app_compose.ui.viewmodal.SharedViewModal
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val sharedViewModal: SharedViewModal by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppComposeTheme {
                val context = LocalContext.current
                val sharedPreferences = context.getSharedPreferences("todo",Context.MODE_PRIVATE)
                var whichPage = sharedPreferences.getString("whichPage","")
                Log.d("whichPage","the value of whichpage is $whichPage")
                Log.d("whichPage","the value of username is ${sharedPreferences.getString("username","")}")
                Log.d("whichPage","the value of passward is ${sharedPreferences.getString("password","")}")
                navController = rememberNavController()
                if (whichPage != null) {
                    SetUpNavigation(
                        context = context,
                        navController = navController,
                        sharedViewModal = sharedViewModal,
                        whichPage = whichPage
                    )
                }

            }
        }
    }
}

