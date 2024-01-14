package com.example.movieinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import kotlinx.coroutines.delay

class SplashView{
    @Composable
    fun SplashScreen(navController: NavController) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.img_splash),
                contentDescription = "img_splash"
            )

            LaunchedEffect(key1 = true) {
                delay(2000)
                navController.navigate("home")
            }
        }
    }
}