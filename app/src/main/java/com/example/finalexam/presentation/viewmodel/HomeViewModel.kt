package com.example.finalexam.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalexam.RetrofitClient
import com.example.finalexam.data.model.Category
import com.example.finalexam.data.model.Song
import com.example.finalexam.data.model.User
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    var categories by mutableStateOf<List<Category>>(emptyList())
    fun fetchCategories() {
        viewModelScope.launch {
                val response = RetrofitClient.categoryApi.getCategories()
                if (response.isSuccessful) {
                    categories = response.body() ?: emptyList()

                }

        }
    }



}