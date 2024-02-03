package com.example.movieinfo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieinfo.base.BaseActivity
import com.example.movieinfo.common.MVUtils
import com.example.movieinfo.common.SampleApplication
import com.example.movieinfo.login.Join
import com.example.movieinfo.login.Login
import com.example.movieinfo.main.Home


class HomeActivity : BaseActivity() {

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            mainScreen(navController = navController)
        }
    }
}


@Composable
fun mainScreen(navController: NavHostController) {
    val context = LocalContext.current
    var userId by remember { mutableStateOf("") }
    var startDestination = if (userId.isNotEmpty()) {
        MVUtils().home
    } else {
        MVUtils().login
    }

    NavHost(navController, startDestination = startDestination) {
        composable(MVUtils().login) { Login().LoginScreen(context, navController) }
        composable(MVUtils().home) { Home().HomeView() }
        composable(MVUtils().join) {Join().JoinScreen(context, navController) }
    }

    LaunchedEffect(true) {
        SampleApplication.getInstance().getDataStore().text.collect {
            userId = it
        }
    }
}


@Composable
fun showLoading() {
    var loading by remember { mutableStateOf(false) }
    if (!loading) return

    CircularProgressIndicator(
        modifier = Modifier.width(64.dp)
    )
}





@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
 //MainScreenView()
}