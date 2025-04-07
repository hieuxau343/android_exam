package com.example.finalexam

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*

import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalexam.components.CustomTextField

class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupScreen()
        }
    }
}

@Composable
fun SignupScreen() {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.background), // Thay bằng ảnh nền của bạn
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Card(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "ĐĂNG KÝ",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF9C89FF)
                )
                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    value = "",
                    onValueChange = {},
                    label = "Tên đăng nhập",
                    icon = Icons.Default.Person
                )
                Spacer(modifier = Modifier.height(8.dp))
                CustomTextField(
                    value = "",
                    onValueChange = {},
                    label = "Mật khẩu",
                    icon = Icons.Default.Lock,
                    isPassword = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                CustomTextField(
                    value = "",
                    onValueChange = {},
                    label = "Xác nhận mật khẩu",
                    icon = Icons.Default.Lock,
                    isPassword = true
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(Color(0xFF9C89FF)),
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Text(text = "ĐĂNG KÝ", color = Color.White, fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Đã có tài khoản, đăng nhập!!",
                    color = Color(0xFF9C89FF),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .align(Alignment.End) // Đưa về bên phải
                        .clickable {
                            val i = Intent(context,LoginActivity::class.java)
                            context.startActivity(i)
                        })
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSignupScreen() {
    SignupScreen()
}