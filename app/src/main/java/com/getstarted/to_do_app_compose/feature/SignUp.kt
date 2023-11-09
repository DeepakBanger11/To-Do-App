package com.getstarted.to_do_app_compose.feature

import android.content.Context
import android.content.SharedPreferences.Editor
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.getstarted.to_do_app_compose.helper.validateSignUpData
import com.getstarted.to_do_app_compose.repositories.PreferencesManager
import com.getstarted.to_do_app_compose.ui.theme.BrandColorPrimary
import com.getstarted.to_do_app_compose.util.Action

@Composable
fun signUp(
    context: Context,
    navigateToListScreen: (Action) -> Unit,
) {
    val preferencesManager = remember { PreferencesManager(context) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var whichPage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        headerForLogin()
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
                .background(BrandColorPrimary)
                .height(350.dp)
        ) {
            Text(
                text = "Register",
                style = TextStyle(
                    fontWeight = FontWeight.Bold, fontSize = 20.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(30.dp, 0.dp, 0.dp, 20.dp),
                color = Color.White,
            )
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Enter email") },
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(7.dp))

            )

            Spacer(modifier = Modifier.size(20.dp))
            TextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("Enter username") },
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(7.dp))

            )

            Spacer(modifier = Modifier.size(20.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Enter Password") },
                modifier = Modifier
                    .background(color = BrandColorPrimary)
                    .clip(shape = RoundedCornerShape(7.dp)),
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Button(
                onClick = {
                    if (validateSignUpData(
                            userName,
                            email,
                            password
                        )
                    ) {
                        preferencesManager.saveData("username",userName)
                        preferencesManager.saveData("email",email)
                        preferencesManager.saveData("password",password)
                        preferencesManager.saveData("whichPage","login")

                        showToast(
                           context, "Registration successful"
                       )
                       navigateToListScreen(Action.NO_ACTION)
                    } else {
                        preferencesManager.saveData("whichPage","signup")
                     showToast(
                          context, "Registration unsuccessful. Please enter all data"
                      )
                    }
                },

                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(BrandColorPrimary),

                ) {
                Text(text = "Register", style = TextStyle(fontWeight = FontWeight.Bold))
            }
        }
    }
}

