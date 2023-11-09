package com.getstarted.to_do_app_compose.feature

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.getstarted.to_do_app_compose.repositories.PreferencesManager
import com.getstarted.to_do_app_compose.ui.theme.BrandColorPrimary


@Composable
fun PasswordAndUsername(
    context: Context,
) {
    val preferencesManager = remember { PreferencesManager(context) }
    if ( preferencesManager.getData("whichPage","") == "password"){
        ForgotPassword(context = context)
    }else
    {
        ForgotUsername(context = context)
    }
}

@Composable
fun ForgotPassword(
    context:Context
) {
    val preferencesManager = remember { PreferencesManager(context) }
    var email by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
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
                .height(300.dp)
        ) {
            Text(
                text = "Display Username",
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
                label = { Text("Enter Username") },
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(7.dp))

            )

            Spacer(modifier = Modifier.size(20.dp))

            Text(
                text = "Password :- " + password,
                modifier = Modifier
                    .height(40.dp),
                textAlign = TextAlign.Justify,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
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
                    if (email == preferencesManager.getData(
                            "email",
                            ""
                        ) && userName == preferencesManager.getData("username", "")
                    ) {
                        password = preferencesManager.getData("username", "")
                        preferencesManager.saveData("whichPage", "login")
                    } else {
                        password = "invalid entry"
                        showToast(
                            context, "invalid entries"
                        )
                    }
                },

                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(BrandColorPrimary),

                ) {
                Text(text = "Generate Password", style = TextStyle(fontWeight = FontWeight.Bold))
            }
        }
    }
}

@Composable
fun ForgotUsername(
    context: Context,
) {
    val preferencesManager = remember { PreferencesManager(context) }
    var email by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }

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
                .height(200.dp)
        ) {
            Text(
                text = "Display Username",
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

            Text(
                text = "user name :- " + userName,
                modifier = Modifier
                    .height(40.dp),
                textAlign = TextAlign.Justify,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
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
                    if (email == preferencesManager.getData("email", "")) {
                        userName = preferencesManager.getData("username", "")
                        preferencesManager.saveData("whichPage", "login")
                    } else {
                        userName = "invalid entry"
                        showToast(
                            context, "no associated email found"
                        )
                    }
                },

                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(BrandColorPrimary),

                ) {
                Text(text = "Generate Username", style = TextStyle(fontWeight = FontWeight.Bold))
            }
        }
    }
}

//@Composable
//@Preview
//fun ForgotUsernamePreview()
//{
//    ForgotPassword()
//}
