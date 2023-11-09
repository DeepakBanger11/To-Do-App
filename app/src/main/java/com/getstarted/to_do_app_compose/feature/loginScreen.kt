@file:OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.getstarted.to_do_app_compose.feature

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.getstarted.to_do_app_compose.ui.theme.BrandColorPrimary
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController
import com.getstarted.to_do_app_compose.R
import com.getstarted.to_do_app_compose.repositories.PreferencesManager
import com.getstarted.to_do_app_compose.util.Action

@ExperimentalMaterial3Api
@Composable
fun loginScreen(
    context: Context,
    navigateToListScreen: (Action) -> Unit,
    navController: NavController,
) {
    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }
    var whichPage by remember { mutableStateOf("") }
    val existingUsername = preferencesManager.getData("username", "default")
    val existingPassword = preferencesManager.getData("password", "default")
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val showPassword = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        headerForLogin()
//            inputForLogin(email,password)
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 40.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 40.dp
                    )
                )
                .background(MaterialTheme.colorScheme.primary)
                .height(350.dp)
        ) {
            Text(
                text = "Login Information",
                style = TextStyle(
                    fontWeight = FontWeight.Bold, fontSize = 20.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(30.dp, 0.dp, 0.dp, 20.dp),
                color = Color.White,
            )
            TextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("Enter username") },
                modifier = Modifier
//               .background(color = BrandColorPrimary)
                    .clip(shape = RoundedCornerShape(7.dp))

            )
            TextButton(onClick = { showToast(context, "coming soon!") }) {
                Text(
                    text = "forgot Username?",
                    fontSize = 15.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .absolutePadding(45.dp, 0.dp, 0.dp, 0.dp),
                    color = Color.White,
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Enter Password") },
                modifier = Modifier
                    .background(color = BrandColorPrimary)
                    .clip(shape = RoundedCornerShape(7.dp)),
                singleLine = true,
                visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (showPassword.value)
                        R.drawable.visibility_
                    else R.drawable.visibility_off_24

                    // Localized description for accessibility services
                    val description =
                        if (showPassword.value) stringResource(id = R.string.hide_password) else stringResource(
                            id = R.string.show_password
                        )
                    IconButton(onClick = { showPassword.value = !showPassword.value }) {
                        Icon(painter = painterResource(image), contentDescription = description)
                    }
                }
            )

            TextButton(onClick = { showToast(context, "coming soon!") }) {
                Text(
                    text = "forgot Password?",
                    fontSize = 15.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .absolutePadding(45.dp, 0.dp, 0.dp, 0.dp),
                    color = Color.White,
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Button(
                onClick = {
                    if (existingUsername.equals(userName) && existingPassword.equals(password) && userName.isNotBlank() && password.isNotBlank()
                    ) {
                        preferencesManager.saveData("whichPage", "list/{action}")
                        showToast(context, "Registration successful!")
                        navigateToListScreen(Action.NO_ACTION)
                    } else if (existingUsername.equals(userName) || existingPassword.equals(password)) {
                        showToast(context, "User or password isn't correct")
                    } else {
                        showToast(context, "Account doesn't exist, Please sign up")
                    }
                },

                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),

                ) {
                Text(text = "Login", style = TextStyle(fontWeight = FontWeight.Bold))
            }
        }
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(onClick = {
                preferencesManager.saveData("whichPage", "signup")
                navController.navigate(route = "signup")
                {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                Log.d("navi", "${preferencesManager.getData("whichPage", "")}")
            }) {
                Text(
                    text = "Don't have an account? Sign Up",
                    fontSize = 15.sp,
                    modifier = Modifier.fillMaxWidth(),
                    color = BrandColorPrimary,
                )
            }
        }

    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Preview
//@Composable
//fun previewLoginScreen() {
//    loginScreen {}
//}

@Composable
fun headerForLogin() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = "To Do",
            modifier = Modifier
                .height(130.dp)
                .padding(20.dp),
            style = MaterialTheme.typography.headlineLarge,
            color = BrandColorPrimary,
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Perfection is only a list away !",
            style = MaterialTheme.typography.headlineMedium,
            color = BrandColorPrimary,
            fontSize = 20.sp,
        )

    }
}


fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}