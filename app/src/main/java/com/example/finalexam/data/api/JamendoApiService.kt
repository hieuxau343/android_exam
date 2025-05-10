package com.example.finalexam.data.api

import com.example.finalexam.data.model.Song
import com.example.finalexam.data.model.SongResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JamendoApiService {
    @GET("tracks")
    suspend fun getSongs(
        @Query("client_id") clientId: String = "aa2033a3",
        @Query("format") format: String = "json",
        @Query("limit") limit: Int = 200
    ): Response<SongResponse>

    @GET("tracks")
    suspend fun getSongById(
        @Query("id") id: Int,
        @Query("client_id") clientId: String = "aa2033a3",
        @Query("format") format: String = "json"
    ): Response<SongResponse>
}