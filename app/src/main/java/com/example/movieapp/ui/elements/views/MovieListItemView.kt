package com.example.movieapp.ui.elements.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.movieapp.data.models.MovieModel
import com.example.movieapp.ui.Page

@Composable
fun MovieListItemView(navController: NavController, movie: MovieModel, modifier: Modifier = Modifier) {
    Box(
        modifier
            .clickable { navController.navigate(Page.MovieDetails.createRoute(movie.id.toString())) }
            ) {
        Column(
            Modifier
                .padding(10.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.image)
                    .crossfade(true)
                    .placeholder(R.drawable.movie_place_holder)
                    .error(R.drawable.movie_place_holder)
                    .build(),
                contentDescription = "movie poster",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Text(
                text = movie.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = movie.release_date,
                fontSize = 14.sp
            )
            Spacer(Modifier.height(2.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = movie.vote,
                    fontSize = 14.sp,
                )
                Image(
                    painter = painterResource(R.drawable.star),
                    contentDescription = "star",
                    modifier = Modifier.padding(2.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        HorizontalDivider(
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .height(0.7.dp)
        )
    }
}
