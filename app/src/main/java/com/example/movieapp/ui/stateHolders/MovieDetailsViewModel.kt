package com.example.movieapp.ui.stateHolders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.models.MovieDetailResponse
import com.example.movieapp.data.repositories.NetworkResult
import com.example.movieapp.data.models.toMovieDetail
import com.example.movieapp.data.repositories.interfaces.IMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailFactory @Inject constructor(private val moviesRepository: IMoviesRepository) {
    suspend fun invoke(id: Int): NetworkResult<MovieDetailResponse> {
        return moviesRepository.getMovieDetails(id)
    }
}

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val movieDetailFactory: MovieDetailFactory) :
    ViewModel() {
    private val _detailResponse: MutableStateFlow<UiState> =
        MutableStateFlow(UiState(true, null, false))
    val movieDetail get() = _detailResponse

    fun getMovieDetail(id: Int) = viewModelScope.launch {
        _detailResponse.emit(UiState(true, null, false))
        when (val response = movieDetailFactory.invoke(id)) {
            is NetworkResult.Success -> {
                _detailResponse.emit(UiState(false, response.value.toMovieDetail(), false))
            }
            is NetworkResult.Failure -> {
                _detailResponse.emit(UiState(false, null, true))
            }

        }
    }
}
