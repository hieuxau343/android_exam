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
    var songs by mutableStateOf<List<Song>>(emptyList())
    var user by mutableStateOf<User?>(null)
    fun fetchCategories() {
        viewModelScope.launch {
                val response = RetrofitClient.categoryApi.getCategories()
                if (response.isSuccessful) {
                    categories = response.body() ?: emptyList()
                }

        }
    }

    fun get_user_info(token : String) {


        viewModelScope.launch {
            val response = RetrofitClient.userApi.getUserInfo("Bearer $token")
            if (response.isSuccessful) {
                user = response.body();
            }

        }

    }
    fun fetchSongs(){
        viewModelScope.launch {
            val response = RetrofitClient.songApi.getSongs()
            if(response.isSuccessful){
                songs = response.body() ?: emptyList()
            }
        }
    }
}