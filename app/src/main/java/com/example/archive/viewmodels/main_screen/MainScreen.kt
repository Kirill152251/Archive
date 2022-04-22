package com.example.archive.viewmodels.main_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.archive.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.example.model.Hero
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
@Destination(start = true)
fun MainScreen(
    navigator: DestinationsNavigator,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewModel.state.isRefreshing)
    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) }
        )
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.onEvent(MainScreenEvent.Refresh) }
        ) {
            when {
                state.isLoading -> ShowProgressBar()
                state.heroes.isNotEmpty() -> {
                    ShowGridLazyColumn(list = state.heroes)
                }
                state.isError -> ShowErrorMassage()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowGridLazyColumn(list: List<Hero>) {
    LazyVerticalGrid(cells = GridCells.Fixed(2), modifier = Modifier.padding(end = 6.dp)) {
        items(list.size) { i ->
            val itemHero = list[i]
            HeroItem(
                hero = itemHero,
                cardHeight = 112f,
                modifier = Modifier
                    .padding(top = 6.dp, start = 6.dp)
                    .fillMaxWidth()
                    .clickable {
                        //TODO: navigate to details screen
                    }
            )
        }
    }
}

@Composable
private fun ShowErrorMassage() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.error_message), color = Color.White)
    }
}

@Composable
private fun ShowProgressBar() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

