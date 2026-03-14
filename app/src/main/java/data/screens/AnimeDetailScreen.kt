package data.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import data.domain.model.server.AnimeDetailModel
import data.navigation.NavRoute
import data.viewModel.server.AnimeDetailViewModel

@Composable
fun AnimeDetailsScreen(
    animeId: Int?,
    navController: NavController,
    viewModel: AnimeDetailViewModel = hiltViewModel()
) {


    LaunchedEffect(animeId) {
        viewModel.loadAnime(animeId?:0)
    }

    val state by viewModel.state.collectAsState()

    when {

        state.isLoading -> {
            CircularProgressIndicator()
        }

        state.error != null -> {
            Text("Error: ${state.error}")
        }

        state.anime != null -> {
            state.anime?.let { anime ->
                AnimeDetailsContent(anime, onClick = {id->
                navController.navigate(NavRoute.Episodes.createRoute(id))
                })
            }
        }
    }

}


@Composable
fun AnimeDetailsContent(
    anime: AnimeDetailModel,
    onClick:(Int)-> Unit
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        item {

            Column {

                // Постер
                AsyncImage(
                    model = anime.images?.jpg?.largeImageUrl
                        ?: anime.images?.jpg?.imageUrl,
                    contentDescription = anime.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Название
                Text(
                    text = anime.title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Информация
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text("Popularity: ${anime.popularity ?: "-"}")
                    Text("Episodes: ${anime.episodes ?: "-"}")
                    Text("Year: ${anime.year ?: "-"}")

                }

                Spacer(modifier = Modifier.height(16.dp))

                // Кнопка смотреть
                Button(
                    onClick = { onClick(anime.id)},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text("Смотреть")
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Описание

                    Text(
                        text = anime.synopsis?:"",
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )


                Spacer(modifier = Modifier.height(16.dp))

                // Жанры
                Text(
                    text = "Genres: " +
                            anime.genres.joinToString { it.name },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Студии
                Text(
                    text = "Studios: " +
                            anime.studios.joinToString { it.name },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(30.dp))

            }

        }

    }
}