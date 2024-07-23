package com.example.movieapp.ui.elements.pages

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movieapp.ui.elements.views.ErrorView
import com.example.movieapp.ui.elements.views.MovieListItemView
import com.example.movieapp.ui.stateHolders.MoviesListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesListPage(navController: NavController, viewModel: MoviesListViewModel = hiltViewModel()) {
    val lazyMovieItems = remember {
        viewModel.movieList
    }.collectAsLazyPagingItems()

    val listState = rememberLazyListState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Movie App") }
            )
        }
    ) {
        if (lazyMovieItems.loadState.refresh is LoadState.Error) {
            ErrorView { lazyMovieItems.retry() }
        }
        LazyColumn(
            state = listState,
            modifier = Modifier.padding(it)
        ) {
            items(lazyMovieItems.itemCount / 3) { index ->
                Row(Modifier.fillMaxWidth()) {
                    repeat(3) { itemIndex ->
                        val movieIndex = index * 3 + itemIndex
                        lazyMovieItems[movieIndex]?.let { movie ->
                            MovieListItemView(
                                navController = navController,
                                movie,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }
    }
}
