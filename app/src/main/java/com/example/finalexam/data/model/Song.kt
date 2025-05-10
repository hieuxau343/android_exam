package com.example.finalexam.data.model

data class Song(
    val id : Int,
    val name: String,
    val image: String,
    val artist_name : String,
    val audio: String
)

data class SongResponse(
    val results: List<Song>
)
