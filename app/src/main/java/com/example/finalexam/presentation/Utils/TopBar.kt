package com.example.finalexam.presentation.Utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onOpenDrawer: () -> Unit, modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black,
        ),
        title = { Text("Music app", color = Color.White ) },
        navigationIcon = {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu",
                 tint = Color.White,
                 modifier = Modifier
                     .padding(start = 16.dp, end = 8.dp)
                     .size(28.dp)
                     .clickable {
                         onOpenDrawer()
                     })
        },

        )
}