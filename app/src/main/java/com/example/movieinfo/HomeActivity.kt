package com.example.movieinfo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieinfo.base.BaseActivity
import com.example.movieinfo.bottomnav.BottomNavItem
import com.example.movieinfo.bottomnav.HomeScreen
import com.example.movieinfo.bottomnav.MyPage
import com.example.movieinfo.bottomnav.TicketList
import com.example.movieinfo.bottomnav.Ticketing
import com.example.movieinfo.login.JoinFragment
import com.example.movieinfo.login.LoginFragment
import com.example.movieinfo.main.MainFragment

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            main(navController)
        }
    }
}


@Composable
fun main(navController: NavHostController) {
    NavHost(navController, startDestination = "login") {
        composable("login"){ LoginFragment().TextFieldTest(navController)}
        composable("main"){ MainFragment().MainView()}
        composable("join"){JoinFragment().TextFieldTest(navController)}
    }
}



@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
 //MainScreenView()
}