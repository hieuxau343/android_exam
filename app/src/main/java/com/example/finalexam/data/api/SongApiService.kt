package com.example.finalexam.data.api

import com.example.finalexam.data.model.Song
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SongApiService {
    @GET("api/songs/display.php")
    suspend fun getSongs() : Response<List<Song>>

    @GET("api/songs/get_song_by_id.php")
    suspend fun getSongById(@Query("id") id: Int): Response<Song>
}