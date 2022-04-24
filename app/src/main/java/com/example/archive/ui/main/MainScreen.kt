package com.example.archive.ui.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
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
import com.example.archive.ui.destinations.DetailsScreenDestination
import com.example.archive.ui.theme.DeepBlue
import com.example.archive.viewmodels.main.MainScreenEvent
import com.example.archive.viewmodels.main.MainScreenViewModel
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
            backgroundColor = DeepBlue,
            title = { Text(text = stringResource(id = R.string.tool_bar_title), color = Color.White) }
        )
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {
                viewModel.onEvent(MainScreenEvent.SearchQueryChange(it))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            placeholder = {
                Text(text = stringResource(id = R.string.search_hint))
            },
            maxLines = 1,
            singleLine = true
        )
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.onEvent(MainScreenEvent.Refresh) }
        ) {
            when {
                state.isLoading -> ShowProgressBar()
                state.heroes.isNotEmpty() -> {
                    ShowGridLazyColumn(list = state.heroes, navigator = navigator)
                }
                state.isError -> ShowErrorMassage()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowGridLazyColumn(list: List<Hero>, navigator: DestinationsNavigator) {
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
                        navigator.navigate(DetailsScreenDestination(itemHero.id))
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
