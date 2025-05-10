package com.example.finalexam.presentation.ui

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.finalexam.R
import com.example.finalexam.presentation.viewmodel.SongViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongPlayScreen(navController : NavController,modifier: Modifier = Modifier, songViewModel: SongViewModel = viewModel(), songId: Int) {
    val context = LocalContext.current
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var isPlaying by remember { mutableStateOf(false) }
    var currentPosition by remember { mutableStateOf(0) }
    var duration by remember { mutableStateOf(1) }
    var sliderPosition by remember { mutableStateOf(0f) }
    var isLooping by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }
    var song = songViewModel.song

    LaunchedEffect(Unit) {
        songViewModel.fetchSongById(songId)
    }


    LaunchedEffect(song) {

        mediaPlayer?.release()
        val player = MediaPlayer()
        try {
            //gan bai hat vao
            player.setDataSource(song?.audio)
            player.prepareAsync()

            player.setOnPreparedListener {
                mediaPlayer = player
                duration = player.duration
                player.isLooping = isLooping
                player.start()
                isPlaying = true
            }

            player.setOnCompletionListener {
                if (isLooping) {
                    player.seekTo(0)
                    player.start()
                } else {
                    isPlaying = false
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()

        }

    }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState,
            containerColor = Color(0xFF1E1E1E),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("💜 Thêm vào Bài hát đã thích", color = Color.White, fontSize = 25.sp)
                Spacer(modifier = Modifier.height(12.dp))
                Text("➕ Thêm vào danh sách phát", color = Color.White, fontSize = 25.sp)
                Spacer(modifier = Modifier.height(12.dp))

//                Text("📀 Chuyển đến album", color = Color.White)
//                Spacer(modifier = Modifier.height(8.dp))
//                Text("🎤 Chuyển đến nghệ sĩ", color = Color.White)
//                Spacer(modifier = Modifier.height(8.dp))

            }
        }
    }


    LaunchedEffect(isPlaying) {
        while (isPlaying) {
            mediaPlayer?.let {
                currentPosition = it.currentPosition
                sliderPosition = currentPosition.toFloat() / duration
            }
            delay(500)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
        }
    }

    LazyColumn(
        modifier = modifier
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
            .fillMaxSize()
            .padding(10.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Close",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp).clickable {
                        navController.popBackStack()

                    }
                )
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More",
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            showSheet = true
                        },

                    )


            }
        }



        item {
            Spacer(modifier = Modifier.height(40.dp))
        }

        item {
            AsyncImage(
                model = song?.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.background),// ảnh tạm thời
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .height(350.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))

        }


        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {
                    song?.name?.let {
                        Text(
                            text = it,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    song?.artist_name?.let { Text(it, fontSize = 15.sp, color = Color.LightGray) }
                }
                Box(
                    modifier = Modifier
                        .size(40.dp) // Kích thước tổng thể (bao gồm viền)
                        .clip(CircleShape) // Bo tròn
                        .border(2.dp, Color.White, CircleShape) // Viền trắng
                        .padding(10.dp) // Cách mép một chút cho icon nhỏ gọn hơn
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color.White,
                        modifier = Modifier.fillMaxSize() // Để icon chiếm toàn bộ phần còn lại trong Box
                    )
                }


            }
            Spacer(modifier = Modifier.height(10.dp))


        }

        item {
            Slider(
                value = sliderPosition,
                onValueChange = { newValue ->
                    sliderPosition = newValue
                    mediaPlayer?.seekTo((duration * newValue).toInt())
                    currentPosition = (duration * newValue).toInt()
                },
                valueRange = 0f..1f,
                modifier = Modifier.fillMaxWidth(),
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTrackColor = Color.White

                )
            )
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(formatMillis(currentPosition), color = Color.White, fontSize = 15.sp)
                Text(formatMillis(duration), color = Color.White, fontSize = 15.sp)
            }

            Spacer(modifier = Modifier.height(14.dp))
        }

        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        painterResource(id = R.drawable.shuffle_variant),
                        contentDescription = "Shuffle",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }

                IconButton(onClick = {}) {
                    Icon(
                        painterResource(id = R.drawable.skip_previous),
                        contentDescription = "Previous",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }

                IconButton(onClick = {
                    mediaPlayer?.let {
                        if (it.isPlaying) {
                            it.pause()
                            isPlaying = false
                        } else {
                            it.start()
                            isPlaying = true
                        }
                    }
                }) {
                    Icon(
                        painter = if (isPlaying) painterResource(id = R.drawable.pause) else painterResource(
                            id = R.drawable.play
                        ),
                        contentDescription = if (isPlaying) "Pause" else "Play",
                        tint = Color.White,
                        modifier = Modifier.size(48.dp)
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        painterResource(id = R.drawable.skip_next),
                        contentDescription = "Next",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
                IconButton(onClick = {
                    isLooping = !isLooping
                    mediaPlayer?.isLooping = isLooping
                }) {
                    Icon(
                        Icons.Default.Refresh,
                        contentDescription = "Refresh",
                        tint = if (isLooping) Color.Green else Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }

            }
        }
    }


}

fun formatMillis(ms: Int): String {
    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%d:%02d".format(minutes, seconds)
}