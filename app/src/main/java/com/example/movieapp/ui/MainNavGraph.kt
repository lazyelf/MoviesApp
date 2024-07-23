
package com.example.movieapp.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.ui.elements.pages.MovieDetailsPage
import com.example.movieapp.ui.elements.pages.MoviesListPage


sealed class Page(val route: String) {
    data object MoviesList : Page("list")
    data object MovieDetails : Page("{movieId}/detail")

    fun createRoute(movieId: String) = "$movieId/detail"
}


@Composable
fun MainNavGraph() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Page.MoviesList.route) {
        composable(route = Page.MoviesList.route) {
            MoviesListPage(navController)
        }
        composable(route = Page.MovieDetails.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(350)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(350)
                )
            }) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")
            requireNotNull(movieId) { "Movie parameter wasn't found. Please make sure it's set!" }
            MovieDetailsPage(navController, movieId)
        }
    }
}