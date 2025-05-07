package com.example.finalexam

import com.example.finalexam.data.api.AuthApiService
import com.example.finalexam.data.api.CategoryApiService
import com.example.finalexam.data.api.SongApiService
import com.example.finalexam.data.api.UserApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:3001/"

    
    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val categoryApi: CategoryApiService by lazy {
        retrofit.create(CategoryApiService::class.java)
    }
    val authApi: AuthApiService by lazy {
        retrofit.create(AuthApiService::class.java)
    }

    val userApi: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }
    val songApi: SongApiService by lazy {
        retrofit.create(SongApiService::class.java)
    }

}