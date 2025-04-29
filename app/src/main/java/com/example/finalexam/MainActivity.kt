package com.example.finalexam

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.finalexam.ui.theme.FinalExamTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.finalexam.presentation.LoginScreen
import com.example.finalexam.presentation.SearchScreen
import com.example.finalexam.presentation.Utils.DrawerBody
import com.example.finalexam.presentation.Utils.DrawerHeader
import com.example.finalexam.presentation.model.MenuItem
import com.example.finalexam.presentation.viewmodel.HomeViewModel
import com.example.finalexam.ui.screens.HomeScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("ContextCastToActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp()
        }
    }
}


@Composable
fun MyApp() {
    val navController = rememberNavController() // <-- Đặt ở đây
    val drawerState = rememberDrawerState(DrawerValue.Closed) // Trạng thái đóng/mở
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val homeViewModel: HomeViewModel = viewModel()
    val currentRoute = remember { mutableStateOf("login_screen") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(Modifier.width(350.dp)) {
                DrawerContent(
                    homeViewModel,
                    navController,
                    scope = scope,
                    drawerState = drawerState,
                    context = context
                )
            }
        },
        gesturesEnabled = true // Bật vuốt để mở menu
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),

            topBar = {
                if (currentRoute.value != "login_screen" && currentRoute.value != "signup_screen") {
                    TopBar(
                        onOpenDrawer = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }
                    )
                }
            }
        ) { paddingValues ->
            appNavHost(
                navController = navController,
                modifier = Modifier.padding(paddingValues),
                onRouteChange = { currentRoute.value = it })
        }


    }
}


@Composable
fun DrawerContent(
    homeViewModel: HomeViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    scope: CoroutineScope,
    drawerState: DrawerState,
    context: Context
) {

    // Đưa DrawerHeader vào đây
    DrawerHeader(homeViewModel, context = context)

    // Đưa DrawerBody vào đây
    val items = listOf(
        MenuItem("1", "Trang chủ", Icons.Default.Home, route = "home_screen"),
        MenuItem(
            "2",
            "Yêu thích",
            Icons.Default.Favorite,
            route = "love_screen",
            iconColor = Color.Red,


            ),
        MenuItem("3", "Tìm kiếm", Icons.Default.Search, "search_screen"),
        MenuItem("4", "Hồ sơ", Icons.Default.Person, "account_screen")

    )
    DrawerBody(items = items) { item ->
        navController.navigate(item.route)
        scope.launch {
            drawerState.close()
        }


    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FinalExamTheme {
        MyApp()
    }
}