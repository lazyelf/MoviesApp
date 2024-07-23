package com.example.movieapp.data.sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.data.FIRST_PAGE
import com.example.movieapp.data.api.MovieApi
import com.example.movieapp.data.models.MovieModel
import com.example.movieapp.data.models.toMovieModel
import java.io.IOException

class MovieDataSource(private val api: MovieApi) : PagingSource<Int, MovieModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val page = params.key ?: FIRST_PAGE
        return try {
            val data = api.getMovies(page)
            LoadResult.Page(
                data = data.moviesList.map { movie ->
                    movie.toMovieModel()
                },
                prevKey = if (page == FIRST_PAGE) null else page - 1,
                nextKey = page + 1
            )
        } catch (t: Throwable) {
            var exception = t
            if (t is IOException) {
                exception = IOException("Check internet connection and try again")
            }
            LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition
    }
}