package data.presentation.screens.animeScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import data.domain.model.profile.FavoriteAnimeModel
import data.domain.model.server.AnimeDetailModel
import data.mapper.animeLocalMapper.toLocal
import data.mapper.animeProfileMapper.toUi
import data.presentation.navigation.mainRoot.NavRoute
import data.presentation.screens.components.AnimeStatus
import data.presentation.screens.components.StatusDropdown
import data.presentation.viewModel.local.FavouriteAnimeViewModel
import data.presentation.viewModel.profile.FavoriteAnimeViewModel
import data.presentation.viewModel.server.AnimeListViewModel


@Composable
fun AnimeListScreen(navController: NavController,
                    viewModel: AnimeListViewModel = hiltViewModel(),
                    localViewModel: FavoriteAnimeViewModel=hiltViewModel()
) {

    val state by viewModel.state.collectAsState()
    val listState = rememberLazyListState()
    val favorites by localViewModel.getFavorites.collectAsState()

    val favoriteMap = remember(favorites) {
        favorites.associateBy { it.mal_id }
    }
    LaunchedEffect(favorites) {
        println("FAVORITES UPDATE: $favorites")
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column() {
            OutlinedTextField(
                value = state.searchQuery,
                onValueChange = {
                    viewModel.onSearchChange(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                placeholder = { Text("Search anime") },
                singleLine = true
            )

            Button(
                onClick = { viewModel.searchAnime() },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Search")
            }

            LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {

                items(state.anime) { anime ->

                    val status = favoriteMap[anime.id]?.status ?: AnimeStatus.NONE

                    Anime(
                        anime = anime,
                        currentStatus = status,
                        onClick = { id ->
                            navController.navigate(
                                NavRoute.AnimeDetails.createRoute(id)
                            )
                        },
                        onStatusChange = { newStatus ->
                            localViewModel.changeStatus(
                                anime = anime.toUi(newStatus), // или маппер
                                status = newStatus
                            )
                        }
                    )
                }

                item {
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }
                }
            }

        }
        LaunchedEffect(listState) {
            snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
                .collect { index ->

                    val lastIndex = state.anime.lastIndex

                    if (index != null && index >= lastIndex - 5) {
                        viewModel.loadAnimeList()
                    }
                }
        }

        state.error?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                modifier = Modifier.align(Alignment.Center),
            )
        }


    }
}


@Composable
fun Anime(
    anime: AnimeDetailModel,
    currentStatus: AnimeStatus,
    onClick: (Int) -> Unit,
    onStatusChange: (AnimeStatus) -> Unit

){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable{onClick(anime.id)}
    ) {

        AsyncImage(
            model = anime.images?.jpg?.largeImageUrl,
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = anime.title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f)
        )

        StatusDropdown(
            currentStatus = currentStatus,
            onStatusSelected = { status ->
                onStatusChange(status)
            }

        )
    }

}



