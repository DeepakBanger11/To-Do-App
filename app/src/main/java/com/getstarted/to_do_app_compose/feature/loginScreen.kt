@file:OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.getstarted.to_do_app_compose.feature

import android.content.Context
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.getstarted.to_do_app_compose.helper.validateRegData
import com.getstarted.to_do_app_compose.ui.theme.BrandColorPrimary
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import com.getstarted.to_do_app_compose.util.Action

@ExperimentalMaterial3Api
@Composable
fun loginScreen(
    context: Context,
    navigateToListScreen: (Action) -> Unit,
) {
    val sharedPreferences = context.getSharedPreferences("todo",Context.MODE_PRIVATE)
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
                value = email,
                onValueChange = { email = it },
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
//                    colors = TextFieldDefaults.te(
//                        textColor = Color.Black)
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
                    if (!validateRegData(
                            context,
                            email,
                            password)) {
//                        showToast(
//                            context, "Registration unsuccessful. Please enter all data."
//                        )
                        navigateToListScreen(Action.NO_ACTION)
                    } else {
//                    saveUserData(context, firstName, lastName, email)
                        //showToast(context, "Registration successful!")
                        // Navigate to Home screen
//
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
                showToast(context, "coming soon!")
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

@ExperimentalMaterial3Api
@Composable
fun inputForLogin(email: String, password: String) {
    var funEmail = email
    var funPassword = password
    val context = LocalContext.current
//    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(5.dp)
//            .clip(
//                shape = RoundedCornerShape(
//                    topStart = 40.dp,
//                    topEnd = 0.dp,
//                    bottomStart = 0.dp,
//                    bottomEnd = 40.dp
//                )
//            )
//            .background(color = BrandColorPrimary)
//            .height(250.dp)
//    ) {
//       TextField(
//           value = email.value, onValueChange ={ email.value = it},
//           label= { Text( "Enter Email")},
//           modifier = Modifier
////               .background(color = BrandColorPrimary)
//               .clip(shape = RoundedCornerShape(7.dp))
//       )
//        Spacer(modifier = Modifier.size(30.dp))
//        TextField(
//            value = funPassword, onValueChange ={ funPassword = it},
//            label= { Text("Enter Password")},
//            modifier = Modifier
//                .background(color = BrandColorPrimary)
//                .clip(shape = RoundedCornerShape(7.dp)),
//            colors = TextFieldDefaults.textFieldColors(
//                textColor = Color.Black
//            )
//        )
//    }
//    Row (
//        horizontalArrangement = Arrangement.SpaceBetween,
//        modifier = Modifier.fillMaxWidth().padding(8.dp)
//    ){
//
//        Button(
//            onClick = {
//                if (!validateRegData(funEmail,funPassword)) {
//                    showToast(
//                        context,
//                        "Registration unsuccessful. Please enter all data."
//                    )
//                    println(funEmail)
//
//                } else {
////                    saveUserData(context, firstName, lastName, email)
//                    showToast(context, "Registration successful!")
//                    println(funEmail)
//                    // Navigate to Home screen
////                    navController.navigate(com.littlelemon.liitlelemon.Home.route)
//                }
//            },
//
//            modifier = Modifier
//                .height(50.dp)
//                .width(150.dp),
//            colors = ButtonDefaults.buttonColors(BrandColorPrimary),
//
//            ) {
//            Text(text = "Login", style = TextStyle(fontWeight = FontWeight.Bold))
//        }
//        Spacer(modifier = Modifier.size(20.dp))
//        Button(
//            onClick = {},
//
//            modifier = Modifier
//                .height(50.dp)
//                .width(150.dp),
//            colors = ButtonDefaults.buttonColors(BrandColorPrimary),
//
//            ) {
//            Text(text = "Sign Up", style = TextStyle(fontWeight = FontWeight.Bold))
//        }
//
//    }
}


 fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}