package com.basaran.whoami.navigation


sealed class Screen(val route: String) {
    object Home : Screen(route = "home_screen")
    object Detail : Screen(route = "detail_screen")
    object Game : Screen(route = "game_screen/")
}