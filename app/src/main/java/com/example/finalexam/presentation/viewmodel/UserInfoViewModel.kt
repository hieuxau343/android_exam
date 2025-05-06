package com.example.finalexam.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalexam.RetrofitClient
import com.example.finalexam.data.model.User
import kotlinx.coroutines.launch

class UserInfoViewModel : ViewModel() {
    var user by mutableStateOf<User?>(null)

    fun get_user_info(token: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.userApi.getUserInfo("Bearer $token")
                if (response.isSuccessful) {
                    val userInfo = response.body()
                    user = userInfo
                } else {
                    Log.e("UserInfo", "Lỗi API: ${response.code()} - ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("UserInfo", "Lỗi khi gọi API: ${e.message}")
            }
        }
    }

}