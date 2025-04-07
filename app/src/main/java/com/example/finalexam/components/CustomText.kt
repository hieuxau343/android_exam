package com.example.finalexam.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun textCustom(name: String,modifier: Modifier = Modifier, color: Color = Color.Black) {
    Text(
        text = name,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = color,
        modifier = modifier
        )
}