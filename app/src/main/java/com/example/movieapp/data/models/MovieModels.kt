package com.example.movieapp.data.models

import com.example.movieapp.data.IMAGE_URL

data class MovieModel(
    val id: Int,
    val title: String,
    val image: String,
    val vote: String,
    val release_date: String
)


data class MovieDetailModel(
    val title: String,
    val image: String,
    val vote: String,
    val release_date: String?,
    val overView: String,
    val genres: List<Genre>
)


fun Movie.toMovieModel() = MovieModel(
    title = title,
    id = id,
    release_date = release_date?.substringBefore("-") ?: "",
    vote = vote_average.toString(),
    image = IMAGE_URL + poster_path
)


fun MovieDetailResponse.toMovieDetail() = MovieDetailModel(
    title = title,
    release_date = release_date?.substringBefore("-") ?: "",
    vote = vote_average.toString(),
    image = IMAGE_URL + poster_path,
    overView = overview,
    genres = genres
)