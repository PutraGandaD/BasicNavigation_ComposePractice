package com.putragandad.basicnavigationcompose.screens.toplevel.foryou

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.putragandad.basicnavigationcompose.R
import com.putragandad.basicnavigationcompose.screens.model.TourismListItem
import com.putragandad.basicnavigationcompose.ui.component.SearchList
import com.putragandad.basicnavigationcompose.ui.theme.BasicNavigationComposeTheme
import com.putragandad.basicnavigationcompose.ui.theme.PureWhite
import com.putragandad.basicnavigationcompose.utils.DummyList

// composable screen for bottom nav bar screen
@Composable
fun ForYouScreen(
    modifier: Modifier = Modifier,
    viewModel: ForYouViewModel = viewModel()
) {
    val uiState : ForYouUiState by viewModel.uiState.collectAsStateWithLifecycle()

    ForYouScreen(
        modifier = modifier,
        uiState = uiState
    )
}

@Composable
fun ForYouScreen(
    modifier: Modifier = Modifier,
    uiState: ForYouUiState
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when(uiState) {
            is ForYouUiState.Loading -> {

            }

            is ForYouUiState.Success -> {
                uiState.data?.let { tourismPlace ->
                    TourismPlaceList(tourismPlace)
                }
            }

            is ForYouUiState.Error -> {
                val message = uiState.message
            }
        }
    }
}

@Composable
fun TourismPlaceList(
    list: List<TourismListItem>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(list, key = { it.id }) { item ->
            Text(
                item.name
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    BasicNavigationComposeTheme {
        ForYouScreen()
    }
}
