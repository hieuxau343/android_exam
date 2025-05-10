import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.finalexam.R
import com.example.finalexam.data.model.Song

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoveScreen(navController: NavController) {
    val context = LocalContext.current
    var searchText by remember { mutableStateOf("") }
    var showSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    val songList = listOf(
        Song(
            1,
            "Fairytale (Hardstyle)",
            "fairytale_cover",
            "BAKI, ZYZZ",
            "https://example.com/fairytale.mp3"
        ),
        Song(2, "Unity", "unity", "TheFatRat", "https://example.com/unity.mp3"),
        Song(3, "On My Way", "on_my_way", "Alan Walker", "https://example.com/on_my_way.mp3"),
        Song(4, "Astronomia", "astronomia", "Tony Igy", "https://example.com/astronomia.mp3")
    )
    val filteredSongs = songList.filter {
        it.name.contains(searchText, ignoreCase = true) ||
                it.artist_name.contains(searchText, ignoreCase = true)
    }
    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState,
            containerColor = Color(0xFF1E1E1E),
            shape = RoundedCornerShape(0.dp), // Loại bỏ bo góc

        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text("Sắp xếp theo", color = Color.White, fontSize = 25.sp)
                Spacer(modifier = Modifier.height(12.dp))
                Text("Tiêu đề", color = Color.White, fontSize = 25.sp)
                Spacer(modifier = Modifier.height(12.dp))
                Text("Nghệ sĩ", color = Color.White, fontSize = 25.sp)
                Spacer(modifier = Modifier.height(12.dp))
                Text("Mới thêm gần đây", color = Color.White, fontSize = 25.sp)
                Spacer(modifier = Modifier.height(12.dp))


            }
        }
    }
    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to Color.Blue,         // Xanh đậm đầu màn
                        0.4f to Color.Black,        // Chuyển dần sang đen
                    )
                )
            )
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        // Icon quay lại
        item {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier.size(28.dp).clickable {
                    navController.popBackStack()
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

        }


        // Thanh tìm kiếm
        item {
            SearchBar(
                showSheet = { showSheet = it },
                searchText = { searchText = it })

        }

        item {
            Spacer(modifier = Modifier.height(25.dp))
        }

        // Tiêu đề + số bài hát + nút phát
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "Bài hát đã thích",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "4 bài hát",
                        fontSize = 16.sp,
                        color = Color.LightGray
                    )
                }

                IconButton(onClick = { /* TODO: Play music */ }) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color.Green)
                    )
                }
            }
            Spacer(Modifier.height(20.dp))
        }

        itemsIndexed(filteredSongs) { index, song ->
            SongItem(song = song, context = context)
            Spacer(modifier = Modifier.height(12.dp))
        }

    }
}

@Composable
private fun SearchBar(showSheet: (Boolean) -> Unit, searchText: (String) -> Unit) {
    var search by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Ô tìm kiếm
        Row(
            modifier = Modifier
                .weight(1f)
                .background(Color(0x40FFFFFF), shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 10.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))

            BasicTextField(
                value = search,
                onValueChange = {
                    search = it
                    searchText(it)
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                decorationBox = { innerTextField ->
                    if (search.isEmpty()) {
                        Text(
                            text = "Tìm bài hát đã thích",
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    innerTextField()
                }
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Nút Sắp xếp
        Box(
            modifier = Modifier
                .background(Color(0x40FFFFFF), shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Sắp xếp",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.clickable {
                    showSheet(true)
                }
            )
        }
    }


}

@Composable
private fun SongItem(song: Song, context: Context) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
//        AsyncImage(
//            model = painterResource(id = R.drawable.background),
//            error = painterResource(id = R.drawable.background),
//            contentDescription = "Song Image",
//            modifier = Modifier
//                .size(70.dp)
//                .clip(RoundedCornerShape(8.dp)),
//            contentScale = ContentScale.Crop
//        )
        Image(
            painter = painterResource(id = R.drawable.background),  // Ảnh từ tài nguyên của bạn
            contentDescription = "Song Image",
            modifier = Modifier
                .size(70.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = song.name,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
            Text(
                text = song.artist_name,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "Options",
            tint = Color.White
        )
    }
}

