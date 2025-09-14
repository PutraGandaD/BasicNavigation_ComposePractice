package com.putragandad.basicnavigationcompose.core.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.putragandad.basicnavigationcompose.core.ui.theme.PureWhite

@Composable
fun TourismPlaceCard(
    idPlace: Int,
    placeName: String,
    onTourismPlaceClick : (Int) -> Unit
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = PureWhite,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 6.dp,
                bottom = 6.dp
            ),
        onClick = {
            onTourismPlaceClick(idPlace)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                placeName
            )
        }
    }
}