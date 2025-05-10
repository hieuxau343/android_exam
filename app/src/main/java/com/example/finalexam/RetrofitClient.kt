package com.example.finalexam

import com.example.finalexam.data.api.AuthApiService
import com.example.finalexam.data.api.CategoryApiService
import com.example.finalexam.data.api.SongApiService
import com.example.finalexam.data.api.UserApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:3000/"
    private const val php_URL = "http://10.0.2.2:/music_app/"

    
    val phpretrofit by lazy {
        Retrofit.Builder()
            .baseUrl(php_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val noderetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val categoryApi: CategoryApiService by lazy {
        phpretrofit.create(CategoryApiService::class.java)
    }
    val authApi: AuthApiService by lazy {
        noderetrofit.create(AuthApiService::class.java)
    }


    val songApi: SongApiService by lazy {
        noderetrofit.create(SongApiService::class.java)
    }

}