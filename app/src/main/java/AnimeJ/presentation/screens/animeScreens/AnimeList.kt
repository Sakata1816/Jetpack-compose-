package AnimeJ.presentation.screens.animeScreens

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
import AnimeJ.domain.model.server.AnimeDetailModel
import AnimeJ.mapper.animeProfileMapper.toUi
import AnimeJ.presentation.navigation.mainRoot.NavRoute
import AnimeJ.presentation.screens.components.AnimeStatus
import AnimeJ.presentation.screens.components.StatusDropdown
import AnimeJ.presentation.viewModel.profile.FavoriteAnimeViewModel
import AnimeJ.presentation.viewModel.server.AnimeListViewModel
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults


@Composable
fun AnimeListScreen(navController: NavController,
                    viewModel: AnimeListViewModel = hiltViewModel(),
                    profileViewModel: FavoriteAnimeViewModel=hiltViewModel()
) {

    val state by viewModel.state.collectAsState()
    val listState = rememberLazyListState()
    val favorites by profileViewModel.getFavorites.collectAsState()

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
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(50.dp),
                placeholder = { Text("Поиск...") },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    ) },
                trailingIcon = {
                    if (state.searchQuery.isNotEmpty()) {
                        IconButton(onClick = { viewModel.onSearchChange("") }) {
                            Icon(Icons.Default.Clear,
                                contentDescription = null)
                        }
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                    cursorColor = MaterialTheme.colorScheme.primary
                ),
                textStyle = MaterialTheme.typography.bodyMedium
            )


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
                            profileViewModel.changeStatus(
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



