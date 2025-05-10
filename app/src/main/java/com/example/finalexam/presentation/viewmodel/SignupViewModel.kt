package com.example.finalexam.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalexam.RetrofitClient
import com.example.finalexam.data.model.SignupRequest
import kotlinx.coroutines.launch
import org.json.JSONObject

class SignupViewModel : ViewModel() {
    var signupState by mutableStateOf<String?>(null)

    fun signup(fullname: String, username: String, password: String, confirmPassword: String) {
        viewModelScope.launch {
            try {
                val request = SignupRequest(fullname, username, password, confirmPassword)
                val response = RetrofitClient.authApi.signup(request)

                if (response.isSuccessful) {
                    // Thành công
                    val message = response.body()?.message ?: "Đăng ký thành công!"

                    signupState = message
                } else {
                    // Lỗi trả về từ server (400, 409,...)
                    val errorBody = response.errorBody()?.string()
                    val message = JSONObject(errorBody).optString("message", "Đăng ký thất bại")
                    signupState = message

                }
            } catch (e: Exception) {
                signupState = "Lỗi kết nối hoặc máy chủ"
            }
        }
    }
}
