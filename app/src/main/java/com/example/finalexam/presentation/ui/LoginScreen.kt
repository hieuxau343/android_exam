package com.example.finalexam.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.finalexam.R
import com.example.finalexam.presentation.Utils.CustomTextField
import com.example.finalexam.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(navController: NavController, modifier: Modifier = Modifier) {


    val context = LocalContext.current
    val viewModel: LoginViewModel = viewModel()
    val loginState = viewModel.loginState


    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Đăng nhập",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        CustomTextField(
            value = userName,
            onValueChange = { userName = it },
            label = "Tên đăng nhập",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        )

        CustomTextField(
            value = password,
            onValueChange = { password = it },
            label = "Mật khẩu",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            visualTransformation = PasswordVisualTransformation()

        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            "Quên mật khẩu", modifier = Modifier

                .padding(vertical = 8.dp)
                .align(Alignment.End),
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.orange)
        )
        Spacer(modifier = Modifier.padding(16.dp))

        Button(
            onClick = {
                if (userName.isNotBlank() && password.isNotBlank()) {
                    viewModel.login(context, userName, password)

                } else {
                    Toast.makeText(context, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_LONG)
                        .show()

                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.orange))
        ) {
            Text("Đăng nhập")
        }

        loginState?.let { i ->
            if (i.isSuccess) {
                LaunchedEffect(Unit) {

                    navController.navigate("home_screen") {
                        popUpTo("login_screen") { inclusive = true }
                    }
                }
            } else {
                Toast.makeText(context, i.exceptionOrNull()?.message, Toast.LENGTH_SHORT).show()
            }
        }
        TextButton(onClick = {
//            Xu ly chuyen sang trang dang ky
            navController.navigate("signup_screen") {
                popUpTo("login_screen") { inclusive = true }
            }


        }) {
            Text("Chưa có tài khoản? Đăng ký", color = colorResource(id = R.color.orange))

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            HorizontalDivider(modifier = Modifier.weight(1f))
            Text(text = "Or", modifier = Modifier.padding(horizontal = 8.dp))

            HorizontalDivider(modifier = Modifier.weight(1f))

        }

        OutlinedButton(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text("Đăng nhập bằng google")
        }


    }


}