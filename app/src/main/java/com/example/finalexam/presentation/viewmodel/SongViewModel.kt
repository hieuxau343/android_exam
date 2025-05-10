package com.example.finalexam.presentation.viewmodel

import JamendoRetrofitClient
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
    var songs by mutableStateOf<List<Song>>(emptyList())

    fun fetchSongById(id: Int) {
        viewModelScope.launch {
            val response = JamendoRetrofitClient.jamendoApi.getSongById(id)
            if (response.isSuccessful) {
                val songResponse = response.body()
                val songList = songResponse?.results

                if (!songList.isNullOrEmpty()) {
                    song = songList[0]
                    Log.e("SongViewModel", "song: $song")
                }
            } else {
                Log.e("SongViewModel", "error: ${response.message()}")
            }
        }
    }

    fun fetchSong() {
        viewModelScope.launch {
            val response = JamendoRetrofitClient.jamendoApi.getSongs()
            if (response.isSuccessful) {
                val body = response.body()
                // Kiểm tra nếu body không null và in ra các bài hát
                body?.results?.let {
                    songs = it
                    it.forEach { song ->
                        Log.e("Song", "Song ID: ${song.id}, Name: ${song.name}")
                    }
                }
            } else {
                Log.e("fetchSong", "Request failed with code: ${response.code()}")
            }
        }
    }


}
