package com.example.finalexam.data.api

import com.example.finalexam.data.model.LoginRequest
import com.example.finalexam.data.model.LoginResponse
import com.example.finalexam.data.model.SignupRequest
import com.example.finalexam.data.model.SignupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("api/login.php")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("api/signup.php")
    suspend fun signup(@Body request: SignupRequest): Response<SignupResponse>
}