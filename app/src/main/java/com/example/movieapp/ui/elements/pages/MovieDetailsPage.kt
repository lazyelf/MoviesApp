package com.example.movieapp.ui.elements.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movieapp.ui.elements.views.ErrorView
import com.example.movieapp.ui.elements.views.Loader
import com.example.movieapp.ui.elements.views.MovieDetailsView
import com.example.movieapp.ui.stateHolders.MovieDetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsPage(
    navController: NavController,
    movieId: String,
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {
    val detail = remember {
        viewModel.getMovieDetail(movieId.toInt())
        viewModel.movieDetail
    }.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Detail") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                when {
                    detail.value.isLoading -> {
                        Loader()
                    }

                    detail.value.data != null -> {
                        MovieDetailsView(detail.value)
                    }

                    detail.value.error -> {
                        ErrorView { viewModel.getMovieDetail(movieId.toInt()) }
                    }
                }
            }
        })
}