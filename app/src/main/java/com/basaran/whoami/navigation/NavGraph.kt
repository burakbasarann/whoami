package com.basaran.whoami.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.basaran.whoami.view.DetailScreen
import com.basaran.whoami.view.GameScreen
import com.basaran.whoami.view.HomeScreen


@Composable
fun NavGraph(savedStateHandle: SavedStateHandle) {

    val controller = rememberNavController()

    NavHost(
        navController = controller,
        startDestination = Screen.Home.route
    ) {

        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController = controller)
        }

        composable(
            route = Screen.Detail.route
        ) {
            DetailScreen(navController = controller, savedStateHandle = savedStateHandle)
        }

        composable(
            route = Screen.Game.route,
        )
        {
            GameScreen(savedStateHandle)
        }
    }
}