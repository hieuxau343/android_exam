package com.example.finalexam.presentation.Utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalexam.R
import com.example.finalexam.SharedPrefsHelper
import com.example.finalexam.presentation.model.MenuItem
import com.example.finalexam.presentation.viewmodel.HomeViewModel
import com.example.finalexam.presentation.viewmodel.UserInfoViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun DrawerHeader(userInfoViewModel: UserInfoViewModel,context: Context) {
    val sharedPrefsHelper = SharedPrefsHelper(context)
    val token = sharedPrefsHelper.getToken()
    val user = userInfoViewModel.user
    Log.e("test",token.toString())
    LaunchedEffect(user) {
        if (!token.isNullOrEmpty()) {

            userInfoViewModel.get_user_info(token)


        }
    }



    Box(
        Modifier.background(
            // Áp dụng màu gradient
            brush = Brush.horizontalGradient(
                colors = listOf(Color(0xFF9C89FF), Color(0xFFFF89A2)) // Màu bắt đầu -> kết thúc
            ),
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                null,
                modifier = Modifier.width(60.dp)

            )
            Spacer(modifier = Modifier.height(20.dp))
            Text("Music app", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = (10.dp)),
                verticalAlignment = Alignment.CenterVertically // Căn giữa theo chiều dọc
            ) {
                Image(
                    painter = painterResource(id = R.drawable.avartar_default),
                    null,
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)

                )

                user?.let {
                    Text(
                        text = it.fullname,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                } ?: Text(
                    text = "Guest",
                    modifier = Modifier.padding(start = 10.dp)
                )

            }
        }
    }
}

@Composable
fun DrawerBody(

    items: List<MenuItem>, itemTextStyle: TextStyle = TextStyle(
        fontSize = 18.sp
    ), onItemClick: (MenuItem) -> Unit
) {
    val selectedItemId = remember { mutableStateOf("1") }
    LazyColumn() {
        items(items) { item ->
            val isSelected = item.id == selectedItemId.value

            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick(item) }
                .background(if (isSelected) Color.LightGray else Color.Transparent)
                .padding(16.dp)) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = "", //Hỗ tro cho người khuyet tat, Talkback
                    tint = item.iconColor
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = item.title, modifier = Modifier.weight(1f), style = itemTextStyle)

            }
        }
    }
}
