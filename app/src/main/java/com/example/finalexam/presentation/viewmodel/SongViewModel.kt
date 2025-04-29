package com.example.finalexam.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalexam.RetrofitClient
import com.example.finalexam.data.model.Song
import com.example.finalexam.data.model.User
import kotlinx.coroutines.launch

class SongViewModel() : ViewModel() {
    var song by mutableStateOf<Song?>(null)

    fun fetchSongById(id: Int) {
        viewModelScope.launch {
            val response = RetrofitClient.songApi.getSongById(id)
            if (response.isSuccessful) {
                song = response.body()
                Log.e("SongViewModel", " song: ${song.toString()}")
            } else {
                Log.e("SongViewModel", "error: ${response.message()}")
            }
        }
    }
}
