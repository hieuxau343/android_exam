package com.example.finalexam.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalexam.RetrofitClient
import com.example.finalexam.SharedPrefsHelper
import com.example.finalexam.data.model.LoginRequest
import com.example.finalexam.data.model.LoginResponse
import kotlinx.coroutines.launch
import org.json.JSONObject

class LoginViewModel() : ViewModel() {
    var loginState by mutableStateOf<String?>(null)

    fun login(context: Context, username: String, password: String) {
        val loginRequest = LoginRequest(username = username, password = password)
        val sharedPrefsHelper = SharedPrefsHelper(context)


        viewModelScope.launch {
            try {
                val response = RetrofitClient.authApi.signin(loginRequest)
                val body = response.body()

                if (response.isSuccessful) {
                    // Thành công
                    val message = response.body()?.message ?: "Đăng nhập thành công!"

                    loginState = message
                    if (body != null) {
                        body.user?.let { sharedPrefsHelper.saveUser(user = it) }
                    }
                } else {
                    // Lỗi trả về từ server (400, 409,...)
                    val errorBody = response.errorBody()?.string()
                    val message = JSONObject(errorBody).optString("message", "Đăng ký thất bại")
                    loginState = message

                }


            } catch (e: Exception) {
            }
        }
    }
}

