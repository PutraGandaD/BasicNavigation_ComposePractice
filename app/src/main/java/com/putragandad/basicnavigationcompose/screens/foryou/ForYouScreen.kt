package com.putragandad.basicnavigationcompose.screens.foryou

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

// composable screen for bottom nav bar screen
@Composable
fun ForYouScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(), // inherit from main modifier to fill max size and edge to edge
        contentAlignment = Alignment.Center
    ) {
        Text("For You Screen")
    }
}
