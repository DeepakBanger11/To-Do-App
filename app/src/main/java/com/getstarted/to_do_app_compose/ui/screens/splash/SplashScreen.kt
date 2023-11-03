package com.getstarted.to_do_app_compose.ui.screens.splash

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.getstarted.to_do_app_compose.R
import com.getstarted.to_do_app_compose.ui.theme.BrandColorPrimary
import com.getstarted.to_do_app_compose.ui.theme.LOGO_HEIGHT
import com.getstarted.to_do_app_compose.util.Constants.SPLASH_SCREEN_DELAY
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToLoginScreen: ()-> Unit
){
    var startAnimation by remember{
        mutableStateOf(false)
    }
    val offsetState by animateDpAsState(
        targetValue = if(startAnimation)0.dp else 200.dp,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    val onsetState by animateDpAsState(
        targetValue = if(startAnimation)0.dp else -200.dp,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    val alphaState by animateFloatAsState(
        targetValue =if(startAnimation)1f else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(SPLASH_SCREEN_DELAY)
        navigateToLoginScreen()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(LOGO_HEIGHT)
                    .offset(y = onsetState)
                    .alpha(alpha = alphaState),
                painter = painterResource(id = R.drawable.todo_list),
                contentDescription = stringResource(id = R.string.to_do_icon))
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier
                    .offset(y = offsetState)
                    .alpha(alpha = alphaState),
                color = BrandColorPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                style = MaterialTheme.typography.titleMedium,
                text = stringResource(id = R.string.to_do_slogon))
        }
    }
}

@Composable
@Preview
fun SplashScreenPreview()
{
    SplashScreen(
        navigateToLoginScreen = {}
    )
}