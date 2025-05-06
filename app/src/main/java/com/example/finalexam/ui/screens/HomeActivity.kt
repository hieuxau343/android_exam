package com.example.finalexam.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.finalexam.R
import com.example.finalexam.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(navController: NavController,modifier: Modifier = Modifier, homeViewModel: HomeViewModel = viewModel()) {

    val imageList = listOf(
        "https://cdn.pixabay.com/photo/2016/09/07/10/37/kermit-1651325_1280.jpg",
        "https://cdn.pixabay.com/photo/2016/09/07/10/37/kermit-1651325_1280.jpg",
        "https://cdn.pixabay.com/photo/2016/09/07/10/37/kermit-1651325_1280.jpg"
    )
    val pagerState = rememberPagerState(pageCount = { imageList.size })
    val categories = homeViewModel.categories
    val list_song = homeViewModel.songs

    LaunchedEffect (Unit){
        homeViewModel.fetchSongs()
    }

    LaunchedEffect(Unit) {
        homeViewModel.fetchCategories()
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(12.dp)
    ) {
//        Slider image
        item {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(end = 20.dp)
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

//

        //categories
        item {
            Column {
                Text(
                    "Thư mục",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 20.dp)
                )

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
                                error = painterResource(id = R.drawable.background),
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

        //
        item {
            Column {
                Text(
                    "Nhạc",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 20.dp)
                )

                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(list_song) { song ->
                        Column(
                            modifier = Modifier.padding(end = 15.dp)
                                .clickable {
                                    //click vao nhac
                                    navController.navigate("play_screen/${song.id}")
                                }
                        ) {
                            AsyncImage(
                                model = song.image_song,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .width(175.dp)
                                    .height(160.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop,
                            )
                            //ten nhac
                            Text(
                                text = song.name_song,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(vertical = 5.dp)
                            )
                            //ten ca si
                            Text(
                                song.singer,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = Color.LightGray
                            )
                        }
                    }
                }
            }

        }
    }


}



