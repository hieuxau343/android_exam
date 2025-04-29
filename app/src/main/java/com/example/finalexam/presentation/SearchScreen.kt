package com.example.finalexam.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.finalexam.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    results: List<String>,
    modifier: Modifier = Modifier
) {
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

@Preview(showBackground = true)
@Composable
fun PreviewSearchScreen() {
    SearchScreen(
        results = listOf("Faded - Alan Walker", "Alone - Alan Walker")
    )
}
