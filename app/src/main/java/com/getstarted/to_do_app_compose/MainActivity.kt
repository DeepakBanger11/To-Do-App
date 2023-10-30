@file:OptIn(ExperimentalMaterial3Api::class)

package com.getstarted.to_do_app_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.getstarted.to_do_app_compose.navigation.SetUpNavigation
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

//               loginScreen()
                navController = rememberNavController()
                SetUpNavigation(
                    navController = navController,
                    sharedViewModal = sharedViewModal
                )

            }
        }
    }
}

