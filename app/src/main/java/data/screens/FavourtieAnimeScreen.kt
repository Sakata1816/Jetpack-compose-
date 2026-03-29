package data.screens

import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.room.util.query
import coil.compose.AsyncImage
import data.domain.model.local.FavoriteAnimeModel
import data.domain.model.server.AnimeDetailModel
import data.navigation.NavRoute
import data.screens.components.AnimeStatus
import data.screens.components.StatusDropdown
import data.viewModel.local.FavouriteAnimeViewModel
import data.viewModel.server.AnimeListViewModel
import kotlinx.coroutines.flow.compose
import java.util.Collections.emptyList
import java.util.Collections.list


@Composable
fun FavouriteAnimeScreen(
    navController: NavController,
    viewModel: FavouriteAnimeViewModel = hiltViewModel()
) {


    var currentScreen by remember { mutableStateOf(FavScreen.Planned) }

    val searchQuery by viewModel.searchQuery.collectAsState()
    val list by viewModel.getFavourite.collectAsState(initial = kotlin.collections.emptyList())

    val listState = rememberLazyListState()


    Box(modifier = Modifier.fillMaxSize()) {

        Column {

            // 🔍 SEARCH
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.onSearchChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                placeholder = { Text("Search favorite anime") },
                singleLine = true
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {
                    currentScreen= FavScreen.Watching
                    viewModel.onStatusFilterChange(AnimeStatus.WATCHING)
                }) {
                    Text("Watching")
                }

                Button(onClick = {
                    currentScreen= FavScreen.Completed
                    viewModel.onStatusFilterChange(AnimeStatus.COMPLETED)

                }) {
                    Text("Completed")
                }
                Button(onClick = {
                    currentScreen= FavScreen.Dropped
                    viewModel.onStatusFilterChange(AnimeStatus.DROPPED)
                }) {
                    Text("Dropped")
                }
                Button(onClick = {
                    currentScreen= FavScreen.Planned
                    viewModel.onStatusFilterChange(AnimeStatus.PLAN)
                }) {
                    Text("Planned")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 📋 LIST
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize()
            ) {

                items(list) { anime ->
                    FavouriteAnime(
                        anime = anime,
                        onClick = { id ->
                            navController.navigate(
                                NavRoute.AnimeDetails.createRoute(id)
                            )
                        },
                        onStatusChange = {newStatus->
                            viewModel.changeStatus(anime, newStatus)
                        },
                        currentStatus = anime.status
                    )
                }
            }
        }

        // ❌ EMPTY STATE
        if (list.isEmpty()) {
            Text(
                text = "Нет избранных",
                modifier = Modifier.align(Alignment.Center)
            )
        }
        }


    }



@Composable
fun FavouriteAnime(anime: FavoriteAnimeModel,
                   onClick:(Int)-> Unit,
                   currentStatus: AnimeStatus,
                   onStatusChange: (AnimeStatus) -> Unit
                   ){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable{onClick(anime.mal_id)}
    ) {

        AsyncImage(
            model = anime.imageUrl,
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

enum class FavScreen {
Planned ,Watching ,Dropped ,Completed
}


fun FavScreen.toStatus(): AnimeStatus {
    return when (this) {
        FavScreen.Planned -> AnimeStatus.PLAN
        FavScreen.Watching -> AnimeStatus.WATCHING
        FavScreen.Completed -> AnimeStatus.COMPLETED
        FavScreen.Dropped -> AnimeStatus.DROPPED
    }
}
