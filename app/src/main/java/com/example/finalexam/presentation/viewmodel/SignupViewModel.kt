package com.example.finalexam.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalexam.RetrofitClient
import com.example.finalexam.data.model.SignupRequest
import com.example.finalexam.data.model.SignupResponse
import kotlinx.coroutines.launch
import kotlin.math.log

class SignupViewModel(): ViewModel() {
    var signupState by mutableStateOf<Result<Unit>?>(null)

    fun signup(fullname:String,username : String,password: String){
        val signupRequest = SignupRequest(fullname,username,password)

        viewModelScope.launch {
            try {
                val response = RetrofitClient.authApi.signup(signupRequest)
                if(response.isSuccessful){
                    val body = response.body()
                    Log.e("body",body.toString())
                    if(body?.status == "success")
                        signupState = Result.success(Unit)
                    else
                        signupState = Result.failure(Exception("Đăng ký không thành công"))
                }
            } catch (e:Exception){
                Log.e("Bug",e.message.toString())
            }
        }

    }
}