package com.example.finalexam.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.finalexam.R
import com.example.finalexam.SharedPrefsHelper
import com.example.finalexam.presentation.Utils.textCustom
import com.example.finalexam.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier, homeViewModel: HomeViewModel = viewModel()) {

    val context = LocalContext.current
    val imageList = listOf(
        "https://cdn.pixabay.com/photo/2016/09/07/10/37/kermit-1651325_1280.jpg",
        "https://cdn.pixabay.com/photo/2016/09/07/10/37/kermit-1651325_1280.jpg",
        "https://cdn.pixabay.com/photo/2016/09/07/10/37/kermit-1651325_1280.jpg"
    )
    val pagerState = rememberPagerState(pageCount = { imageList.size })
    val categories = homeViewModel.categories

    LaunchedEffect(Unit) {
        homeViewModel.fetchCategories()
    }

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
//        Slider image
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
                        .clip(RoundedCornerShape(16.dp))
                        .clickable {
                            // Xử lý sự kiện click
                        }
                )
            }
        }

//        Thu muc/ xem them
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),

                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                textCustom("Thư mục")
                textCustom("Xem thêm", color = Color.Blue)
            }
        }

        //categories
        item {

            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(categories) { category ->
                    Box(
                        modifier = Modifier
                            .width(200.dp)
                            .height(100.dp)
                            .padding(end = 12.dp)
                            .clickable {
                                // Xử lý sự kiện click
                            }
                    ) {
                        AsyncImage(
                            model = category.image_category,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(16.dp)),
                            contentScale = ContentScale.Crop,
                        )

                        Text(
                            text = category.name_category,
                            color = Color.White,
                            fontSize = 18.sp,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }


    }
}


