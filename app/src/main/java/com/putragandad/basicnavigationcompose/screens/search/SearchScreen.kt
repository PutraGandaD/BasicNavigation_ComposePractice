package com.putragandad.basicnavigationcompose.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.putragandad.basicnavigationcompose.R
import com.putragandad.basicnavigationcompose.screens.foryou.ForYouScreen
import com.putragandad.basicnavigationcompose.ui.component.SearchList
import com.putragandad.basicnavigationcompose.ui.theme.BasicNavigationComposeTheme
import com.putragandad.basicnavigationcompose.utils.DummyList

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(DummyList.dummySearchItems, key = { it.id }) { item ->
                SearchList(
                    author = item.author,
                    shortDescription = item.description,
                    image = painterResource(id = R.drawable.ic_blank_profile),
                    onClickPress = { /* handle click for item.id */ }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    BasicNavigationComposeTheme {
        SearchScreen()
    }
}