package com.example.movieinfo.main

import android.annotation.SuppressLint
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieinfo.R
import com.example.movieinfo.SplashView
import com.example.movieinfo.base.BaseFragment
import com.example.movieinfo.bottomnav.BottomNavItem
import com.example.movieinfo.bottomnav.HomeScreen
import com.example.movieinfo.bottomnav.MyPage
import com.example.movieinfo.bottomnav.TicketList
import com.example.movieinfo.bottomnav.Ticketing

class MainFragment : BaseFragment() {
    override fun initView() {

    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun MainView() {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = { BottomNavigations(navController = navController) }
        ) {
            NavigationGraph(navController = navController)
        }
    }


    @Composable
    fun BottomNavigations(navController: NavController) {
        val items = listOf(
            BottomNavItem.Home,
            BottomNavItem.Ticketing,
            BottomNavItem.TicketList,
            BottomNavItem.MyPage,
        )
        BottomNavigation(
            backgroundColor = colorResource(id = R.color.white),
            contentColor = Color.Black
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(id = item.icon),
                            contentDescription = item.title
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            fontSize = 9.sp
                        )
                    },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.Black.copy(0.4f),
                    alwaysShowLabel = true,
                    selected = currentRoute == item.screen_route,
                    onClick = {
                        navController.navigate(item.screen_route) {

                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }

    @Composable
    fun NavigationGraph(navController: NavHostController) {
        NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
            composable(BottomNavItem.Home.screen_route) {
                HomeScreen()
            }
            composable(BottomNavItem.Ticketing.screen_route) {
                Ticketing()
            }
            composable(BottomNavItem.TicketList.screen_route) {
                TicketList()
            }
            composable(BottomNavItem.MyPage.screen_route) {
                MyPage()
            }
        }
    }


}