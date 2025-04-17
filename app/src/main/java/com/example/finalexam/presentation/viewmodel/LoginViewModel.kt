package com.example.finalexam.presentation.viewmodel

import android.annotation.SuppressLint
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
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {
    var loginState by mutableStateOf<Result<Unit>?>(null)

    fun login(context: Context, username: String, password: String) {
        val loginRequest = LoginRequest(username = username, password = password)
        val sharedPrefsHelper = SharedPrefsHelper(context)


        viewModelScope.launch {
            try {
                val response = RetrofitClient.authApi.login(loginRequest)
                if (response.isSuccessful) {
                    val body = response.body()
                    Log.e("body",body.toString())
                    if(body?.status == "success" ) {
                        sharedPrefsHelper.saveToken(body.token)
                        loginState = Result.success(Unit)

                        Log.e("prefs",sharedPrefsHelper.getToken()?:"null")


                    }
                    else
                        loginState = Result.failure(Exception("Sai tài khoản hoặc mật khẩu"))

                }
            } catch (e: Exception) {
                loginState = Result.failure(e)
            }
        }
    }
}

