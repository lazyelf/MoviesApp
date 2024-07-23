package com.example.movieapp.ui.elements.pages

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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

    val listState = rememberLazyGridState()
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
        LazyVerticalGrid(state = listState,
            modifier = Modifier.padding(it),
            columns = GridCells.Fixed(count = 3)
        ) {
            items(lazyMovieItems.itemCount) { index ->
                lazyMovieItems[index]?.let { movie ->
                    MovieListItemView(navController = navController, movie)
                }
            }
        }
    }
}
