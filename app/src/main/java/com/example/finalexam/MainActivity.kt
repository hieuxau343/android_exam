package com.example.finalexam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.finalexam.ui.theme.FinalExamTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.finalexam.components.DrawerBody
import com.example.finalexam.components.DrawerHeader
import com.example.finalexam.model.MenuItem
import com.example.finalexam.ui.screens.HomeScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp()

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val navController = rememberNavController() // <-- Đặt ở đây
    val drawerState = rememberDrawerState(DrawerValue.Closed) // Trạng thái đóng/mở
    val scope = rememberCoroutineScope()


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(Modifier.width(250.dp)) {
                DrawerContent(navController, scope = scope, drawerState = drawerState)
            }
        },
        gesturesEnabled = true // Bật vuốt để mở menu
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
//
                TopBar(
                    onOpenDrawer = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    },
                )

            }
        ) { paddingValues ->
            AppNavHost(navController = navController, modifier = Modifier.padding(paddingValues))
        }


    }
}


@Composable
fun DrawerContent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    scope: CoroutineScope,
    drawerState: DrawerState
) {

    // Đưa DrawerHeader vào đây
    DrawerHeader()

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