package com.example.movieapp.data.repositories.interfaces

import androidx.paging.PagingData
import com.example.movieapp.data.models.MovieDetailResponse
import com.example.movieapp.data.models.MovieModel
import com.example.movieapp.data.repositories.NetworkResult
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {
    fun getMovies(): Flow<PagingData<MovieModel>>
    suspend fun getMovieDetails(id: Int): NetworkResult<MovieDetailResponse>
}
