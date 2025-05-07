package com.example.finalexam.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
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
import com.example.finalexam.presentation.viewmodel.SignupViewModel

@Composable
fun SignUpScreen(navController: NavController, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val viewModel: SignupViewModel = viewModel()
    val signupState = viewModel.signupState
    var fullName by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(
            text = "Đăng ký",
            fontSize = 24.sp,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(vertical = 16.dp)
        )

        CustomTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = "Họ và tên",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),


            )
        CustomTextField(
            value = userName,
            onValueChange = { userName = it },
            label = "Tên đăng nhập",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),


            )
        CustomTextField(
            value = password,
            onValueChange = { password = it },
            label = "Mật khẩu",
            leadingIcon = Icons.Default.Lock,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            visualTransformation = PasswordVisualTransformation(),


            )
        CustomTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Xác nhận mật khẩu",
            leadingIcon = Icons.Default.Lock,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            visualTransformation = PasswordVisualTransformation(),


            )

        Button(
            onClick = {
                if (fullName.isNotBlank() && userName.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank()) {
                        viewModel.signup(fullName, userName, password,confirmPassword)

                } else {
                    Toast.makeText(context, "Nhập thông tin đầy đủ", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.orange))
        ) {
            Text("Đăng ký", color = colorResource(id = R.color.white))
        }

        signupState?.let { message ->
            LaunchedEffect(message) {

                    Toast.makeText(context, "ab"+message, Toast.LENGTH_SHORT).show()
                    // Reset fields
                    fullName = ""
                    userName = ""
                    password = ""
                    confirmPassword = ""

            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {

            TextButton(onClick = {
                //Xu ly an chuyen trang sang dang nhap
                navController.navigate("login_screen") {
                    popUpTo("signup_screen") { inclusive = true }
                }
            }) {
                Text("Đã có tài khoản? Đăng nhập", color = colorResource(id = R.color.orange))
            }
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