package com.example.movieinfo.bottomnav

import com.example.movieinfo.R
import com.example.movieinfo.R.*


sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem("Home", drawable.ic_home,"home")
    object Ticketing: BottomNavItem("Ticketing", drawable.ic_ticketing,"ticketing")
    object TicketList: BottomNavItem("TicketList", drawable.ticket_list,"ticket_list")
    object MyPage: BottomNavItem("MyPage", drawable.my_page,"my_page")
}
