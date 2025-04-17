package com.example.finalexam.presentation.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val id : String,
    val title: String,
    val icon: ImageVector,
    val route: String = "",
    val iconColor: Color = Color.Black,

)
