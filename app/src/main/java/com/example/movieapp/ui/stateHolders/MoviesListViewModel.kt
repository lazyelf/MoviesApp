package com.example.movieapp.ui.stateHolders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.data.models.MovieModel
import com.example.movieapp.data.repositories.interfaces.IMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesListFactory @Inject constructor(private val moviesRepository: IMoviesRepository) {
    fun invoke(): Flow<PagingData<MovieModel>> {
        return moviesRepository.getMovies()
    }
}

@HiltViewModel
class MoviesListViewModel @Inject constructor(private val moviesListFactory: MoviesListFactory) :
    ViewModel() {

    val movieList: Flow<PagingData<MovieModel>> get() = getMovies()
    private fun getMovies() = moviesListFactory.invoke().cachedIn(viewModelScope)
}
