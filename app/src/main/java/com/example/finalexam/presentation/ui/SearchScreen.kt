package com.example.finalexam.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.finalexam.R

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier
) {
    var results: List<String> = listOf("Faded - Alan Walker", "Alone - Alan Walker")

    var query by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFAAAAAA),
                        Color.Black
                    ), // Định nghĩa màu chuyển tiếp từ trắng sang đen
                    start = Offset(0f, 0f),  // Điểm bắt đầu gradient (trên cùng)
                    end = Offset(0f, Float.POSITIVE_INFINITY) // Điểm kết thúc gradient (dưới cùng)
                )
            )
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Tìm kiếm bài hát...", color = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black
            )
        )


        Spacer(modifier = Modifier.height(16.dp))

        if (!query.isEmpty()) {
        } else {
            LazyColumn {
                items(results) { item ->
                    Row(
                        Modifier
                            .padding(vertical = 10.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically


                    ) {
                        AsyncImage(
                            model = "",
                            contentDescription = "a",
                            error = painterResource(id = R.drawable.background),
                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp)

                        )
                        Column(
                            Modifier
                                .padding(start = 20.dp)
                                .height(80.dp)
                                .weight(1f), verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("abc", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            Text("lol")
                        }
                    }
                }
            }
        }
    }
}