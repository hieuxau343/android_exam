package com.example.finalexam

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalexam.presentation.LoginScreen
import com.example.finalexam.presentation.SignUpScreen
import com.example.finalexam.ui.screens.AccountScreen
import com.example.finalexam.ui.screens.HomeScreen
import com.example.finalexam.ui.screens.LoveScreen
import com.example.finalexam.ui.screens.SearchScreen

@Composable
fun appNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = "signup_screen", modifier = modifier) {
        composable("home_screen") { HomeScreen(Modifier.padding(12.dp)) }
        composable("love_screen") { LoveScreen() }
        composable("search_screen") { SearchScreen() }
        composable("account_screen") { AccountScreen() }
        composable("signup_screen"){ SignUpScreen(navController) }
        composable("login_screen"){ LoginScreen(navController,) }
    }
}

