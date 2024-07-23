package com.example.movieapp.data.api

import com.example.movieapp.data.models.MovieDetailResponse
import com.example.movieapp.data.models.MoviesListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("page") page: Int? = null,
    ): MoviesListResponse

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: Int
    ): MovieDetailResponse
}