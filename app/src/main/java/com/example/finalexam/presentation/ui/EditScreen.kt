package com.example.finalexam.presentation.ui

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.finalexam.R

@Preview(showBackground = true)
@Composable
fun EditScreen(
//    navController: NavController
    modifier: Modifier = Modifier
) {
    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            IconButton(onClick = {
                //quay ve trang account_screen
//                navController.navigate("account_screen")
            }) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)


                )
            }
            Text(
                "Chỉnh sửa hồ sơ",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                "Lưu",
                color = Color.LightGray,
                fontSize = 20.sp,
                modifier = Modifier.clickable {
                    //Lưu
                })
        }

        Box(
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.BottomEnd // icon nằm dưới bên phải
        ) {
            // Ảnh đại diện (dạng tròn)
            AsyncImage(
                model = "",
                error = painterResource(id = R.drawable.background), // thay bằng ảnh bạn muốn
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(2.dp,Color.Transparent, CircleShape)
            )

            // Nút chỉnh sửa (icon cây bút trong ô trắng tròn)
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(Color.White, CircleShape)
                    .border(1.dp, Color.Gray, CircleShape)
                    .clickable { /* mở chọn ảnh */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        Spacer(Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Tên",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(Modifier.padding(start = 30.dp))
            Text(
                text = "    hieuxau123", // thêm khoảng trắng để cách ra hoặc dùng Spacer
                color = Color.LightGray,
                fontSize = 18.sp
            )
        }

        // Gạch dưới
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(9.dp)
                .padding(top = 10.dp)
                .alpha(0.3f)
                .background(Color.LightGray)
        )
    }
}