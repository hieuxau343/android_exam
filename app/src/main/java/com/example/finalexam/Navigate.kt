package com.example.finalexam

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalexam.ui.screens.AccountScreen
import com.example.finalexam.ui.screens.HomeScreen
import com.example.finalexam.ui.screens.LoveScreen
import com.example.finalexam.ui.screens.SearchScreen

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = "home_screen", modifier = modifier) {
        composable("home_screen") { HomeScreen() }
        composable("love_screen") { LoveScreen() }
        composable("search_screen") { SearchScreen() }
        composable("account_screen") { AccountScreen() }
    }
}