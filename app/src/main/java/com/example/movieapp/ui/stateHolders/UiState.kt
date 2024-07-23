package com.example.movieapp.ui.stateHolders

import com.example.movieapp.data.models.MovieDetailModel

data class UiState(
    val isLoading: Boolean = false,
    val data: MovieDetailModel? = null,
    val error: Boolean = false
)