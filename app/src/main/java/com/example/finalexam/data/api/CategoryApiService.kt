package com.example.finalexam.data.api

import com.example.finalexam.data.model.Category
import retrofit2.Response
import retrofit2.http.GET

interface CategoryApiService {

    @GET("api/categories/display.php")
    suspend fun getCategories() : Response<List<Category>>
}