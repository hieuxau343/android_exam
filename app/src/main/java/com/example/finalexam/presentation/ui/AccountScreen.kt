package com.example.finalexam.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.finalexam.R
import com.example.finalexam.SharedPrefsHelper
import com.example.finalexam.presentation.viewmodel.UserInfoViewModel

@Preview(showBackground = true)
@Composable
fun AccountScreen(navController: NavController) {
    val userInfoViewModel: UserInfoViewModel = viewModel()
    val context = LocalContext.current
    val sharedPrefsHelper = SharedPrefsHelper(context)
    val token = sharedPrefsHelper.getToken()
    val user = userInfoViewModel.user

    LaunchedEffect(token) {
        if (!token.isNullOrEmpty()) {
            userInfoViewModel.get_user_info(token)
        }
    }

    LazyColumn(
        Modifier
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFAAAAAA),
                        Color.Black
                    ), // Định nghĩa màu chuyển tiếp từ trắng sang đen
                    start = Offset(0f, 0f),  // Điểm bắt đầu gradient (trên cùng)
                    end = Offset(0f, Float.POSITIVE_INFINITY) // Điểm kết thúc gradient (dưới cùng)
                )
            )
            .fillMaxSize()
            .padding(10.dp)
    ) {

        item {
            Row(
                modifier = Modifier.clickable {
                    navController.navigate("edit_screen")

                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = "", //dien user vao day user.image
                    contentDescription = "",
                    error = painterResource(id = R.drawable.background),
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape),

                    )
                Column(modifier = Modifier.padding(start = 15.dp)) {
                    Text(
                        "hieuxau123",
                        fontSize = 25.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.LightGray)) {
                                append("Đang theo dõi ")
                            }
                            withStyle(style = SpanStyle(color = Color.White)) {
                                append("3")
                            }
                        },
                        fontSize = 20.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

        }
        item {
            Text(
                "Chỉnh sửa",
                modifier = Modifier
                    .border(width = 1.dp, color = Color.LightGray, shape = CircleShape)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clickable {
                        navController.navigate("edit_screen")
                    },
                color = Color.White, fontWeight = FontWeight.Bold
            )
        }
    }
}
