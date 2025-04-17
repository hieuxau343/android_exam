package com.example.finalexam.data.api

import com.example.finalexam.data.model.User
import retrofit2.Response
import retrofit2.http.POST

interface UserApiService {
    @POST("api/get_user_info.php")
    suspend fun getUserInfo(
        @retrofit2.http.Header("Authorization") token: String): Response<User>

}