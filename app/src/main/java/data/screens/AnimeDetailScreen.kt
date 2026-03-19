package data.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
    val navController = rememberNavController()
    var currentScreen by remember { mutableStateOf(Screen.Screen1) }


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

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = {
/*
                        navController.navigate("screen1")
*/
                        currentScreen= Screen.Screen1
                    }) {
                        Text("Экран 1")
                    }

                    Button(onClick = {
/*
                        navController.navigate("screen2")
*/
                        currentScreen= Screen.Screen2
                    }) {
                        Text("Экран 2")
                    }
                }


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
                    Text("Year: ${anime.year ?: "-"}")

                }

                Spacer(modifier = Modifier.height(16.dp))

                // Кнопка смотреть
                Button(
                    onClick = { onClick(anime.id) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text("Смотреть")
                }

                Spacer(modifier = Modifier.height(20.dp))



                // Описание
                BottomContentSection(currentScreen,anime)



                /*Box(
                    modifier = Modifier
                        .fillMaxWidth(),

                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "screen1",
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        composable("screen1") {
                            AnimeDownInfo(anime)
                        }
                        composable("screen2") {

                        }
                    }
                }*/

            }

        }
    }
}



@Composable
fun AnimeDownInfo(anime: AnimeDetailModel){
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text =  "Описание",
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(9.dp))

        Text(
            text =  anime.synopsis?:"",
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

@Composable
fun AnimeDownCharacters(){

}

@Composable
fun AnimeDownVideos(){

}

@Composable
fun AnimeDownNews(){

}

@Composable
fun BottomContentSection(screen: Screen,anime: AnimeDetailModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        when (screen) {
            Screen.Screen1 -> AnimeDownInfo(anime)
            Screen.Screen2 -> AnimeDownNews()
        }
    }
}

enum class Screen {
    Screen1, Screen2
}
