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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import data.domain.model.server.CharacterItemModel
import data.domain.model.server.CharactersResponseModel
import data.navigation.NavRoute
import data.viewModel.server.AnimeCharactersViewModel
import data.viewModel.server.AnimeDetailViewModel

@Composable
fun AnimeDetailsScreen(
    animeId: Int?,
    navController: NavController,
    viewModel: AnimeDetailViewModel = hiltViewModel(),
) {

    LaunchedEffect(animeId) {
        viewModel.loadAnime(animeId?:0)
        viewModel.loadCharacters(animeId?:0)
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
                AnimeDetailsContent( anime=anime, characters = state.characters, onClick = {id->
                navController.navigate(NavRoute.Episodes.createRoute(id))
                })
            }
        }

    }


}


@Composable
fun AnimeDetailsContent(
    anime: AnimeDetailModel,
    characters: List<CharacterItemModel>,
    onClick:(Int)-> Unit
) {
    var currentScreen by remember { mutableStateOf(Screen.Description) }


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

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = {
                        currentScreen= Screen.Description
                    }) {
                        Text("Info")
                    }

                    Button(onClick = {
                        currentScreen= Screen.Characters
                    }) {
                        Text("Characters")
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }

            }
        when (currentScreen) {
            Screen.Description -> {
                item {
                    AnimeDownInfo(anime)
                }
            }
            Screen.Characters -> {
                items(characters) { character ->
                    Row(){
                        AsyncImage(
                            model = character.images?.jpg ?.image_url ?: character.images?.webp?.image_url,
                            contentDescription = "AnimeChatactersImage",
                            modifier = Modifier.size(120.dp),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.width(12.dp))
                        Column() {
                            Text(text = character.name)
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(text = character.role)
                        }
                    }
                }
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


/*@Composable
fun BottomContentSection(screen: Screen,anime: AnimeDetailModel,characters: List<CharacterItemModel>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        when (screen) {
            Screen.Screen1 -> AnimeDownInfo(anime)
            Screen.Screen2 -> AnimeCharactersContetnt(characters)
        }
    }
}*/

enum class Screen {
    Description, Characters
}
