package com.example.movieapp.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.data.api.MovieApi
import com.example.movieapp.data.models.MovieDetailResponse
import com.example.movieapp.data.models.MovieModel
import com.example.movieapp.data.repositories.interfaces.IMoviesRepository
import com.example.movieapp.data.sources.MovieDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api: MovieApi) : IMoviesRepository,
    BaseRepository() {
    private val pageSize = 25
    override fun getMovies(): Flow<PagingData<MovieModel>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = pageSize),
            pagingSourceFactory = {
                MovieDataSource(api)
            }
        ).flow
    }

    override suspend fun getMovieDetails(id: Int): NetworkResult<MovieDetailResponse> {
        return safeApiCall { api.getMovieDetail(id) }
    }
}