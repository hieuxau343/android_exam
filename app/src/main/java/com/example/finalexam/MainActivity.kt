package com.example.finalexam

import android.annotation.SuppressLint
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
import com.example.finalexam.presentation.Utils.DrawerBody
import com.example.finalexam.presentation.Utils.DrawerHeader
import com.example.finalexam.presentation.model.MenuItem
import com.example.finalexam.presentation.ui.AccountScreen
import com.example.finalexam.presentation.viewmodel.UserInfoViewModel
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
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val userInfoViewModel: UserInfoViewModel = viewModel()
    val currentRoute = remember { mutableStateOf("login_screen") }

    // Kiểm tra route hiện tại
    val checked = currentRoute.value == "login_screen" || currentRoute.value == "signup_screen" || currentRoute.value == "edit_screen"

    if (checked) {
        // 👉 Nếu là login hoặc signup → KHÔNG có drawer, không có topbar
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { paddingValues ->
            appNavHost(
                navController = navController,
                modifier = Modifier.padding(paddingValues),
                onRouteChange = { currentRoute.value = it }
            )
        }
    } else {
        // 👉 Nếu là các màn còn lại → Có drawer, có topbar
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(Modifier.width(350.dp)) {
                    DrawerContent(
                        userInfoViewModel,
                        navController,
                        scope = scope,
                        drawerState = drawerState,
                        context = context
                    )
                }
            },
            gesturesEnabled = true
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopBar(
                        onOpenDrawer = {
                            scope.launch {
                                if (drawerState.isClosed) drawerState.open() else drawerState.close()
                            }
                        }
                    )
                }
            ) { paddingValues ->
                appNavHost(
                    navController = navController,
                    modifier = Modifier.padding(paddingValues),
                    onRouteChange = { currentRoute.value = it }
                )
            }
        }
    }
}



@Composable
fun DrawerContent(
    userInfoViewModel: UserInfoViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    scope: CoroutineScope,
    drawerState: DrawerState,
    context: Context
) {

    // Đưa DrawerHeader vào đây
    DrawerHeader(userInfoViewModel = userInfoViewModel, context = context)

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