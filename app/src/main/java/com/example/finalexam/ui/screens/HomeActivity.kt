package com.example.finalexam.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.finalexam.R
import com.example.finalexam.components.textCustom

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val imageList = listOf(
        "https://cdn.pixabay.com/photo/2016/09/07/10/37/kermit-1651325_1280.jpg",
        "https://cdn.pixabay.com/photo/2016/09/07/10/37/kermit-1651325_1280.jpg",
        "https://cdn.pixabay.com/photo/2016/09/07/10/37/kermit-1651325_1280.jpg"
    )
    val pagerState = rememberPagerState(pageCount = { imageList.size })
    val items = (1..50).toList() // Danh sách demo

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) { currentPage ->
                AsyncImage(
                    model = imageList[currentPage],
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    error = painterResource(id = R.drawable.background),
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            // Xử lý sự kiện click
                        }
                )
            }
        }

        item {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()

                ,

                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                textCustom("Thư mục")
                textCustom("Xem thêm", color = Color.Blue)
            }
        }

        item {
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(4) { index ->
                    Box(
                        modifier = Modifier
                            .width(200.dp)
                            .height(100.dp)
                            .padding(horizontal = 8.dp)
                            .clickable {
                                // Xử lý sự kiện click
                            }
                    ) {
                        AsyncImage(
                            model = R.drawable.background,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(16.dp)),
                            contentScale = ContentScale.Crop,
                        )

                        Text(
                            text = "Chữ $index",
                            color = Color.White,
                            fontSize = 18.sp,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }

        // LazyVerticalGrid nằm trong LazyColumn và sẽ cuộn cùng với các phần tử khác
        item {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth() // Cho phép grid chiếm hết chiều rộng
                    .heightIn(max= 1000.dp)
                    .padding(8.dp),
                columns = GridCells.Fixed(2), // Chia thành 2 cột
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items.size) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f) // Giữ tỉ lệ vuông
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Item ${items[index]}")
                    }
                }
            }
        }
    }
}


