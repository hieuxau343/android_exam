package com.example.finalexam.presentation.Utils

import LoveScreen
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalexam.presentation.ui.LoginScreen
import com.example.finalexam.presentation.ui.SignUpScreen
import com.example.finalexam.presentation.ui.SongPlayScreen
import com.example.finalexam.presentation.ui.AccountScreen
import com.example.finalexam.presentation.ui.EditScreen
import com.example.finalexam.presentation.ui.HomeScreen
import com.example.finalexam.presentation.ui.SearchScreen

@Composable
fun appNavHost(navController: NavHostController, modifier: Modifier, onRouteChange: (String) -> Unit) {
    NavHost(navController = navController, startDestination = "signup_screen", modifier = modifier) {
        composable("home_screen") {
            onRouteChange("home_screen")
            HomeScreen(navController)
        }
        composable("love_screen") {
            onRouteChange("love_screen")
            LoveScreen(navController)
        }
        composable("search_screen") {
            onRouteChange("search_screen")
            SearchScreen()
        }
        composable("account_screen") {
            onRouteChange("account_screen")
            AccountScreen(navController)
        }
        composable("signup_screen") {
            onRouteChange("signup_screen")
            SignUpScreen(navController)
        }
        composable("login_screen") {
            onRouteChange("login_screen")
            LoginScreen(navController)
        }
        composable("edit_screen") {
            onRouteChange("edit_screen")
            EditScreen(
                navController
            )
        }
        composable("play_screen/{songId}") { backStackEntry ->
            val songId = backStackEntry.arguments?.getString("songId")?.toIntOrNull()
            songId?.let {
                onRouteChange("play_screen/$it")
                Log.e("SongID", it.toString())
                SongPlayScreen(navController,songId = it)
            }
        }

    }
}
