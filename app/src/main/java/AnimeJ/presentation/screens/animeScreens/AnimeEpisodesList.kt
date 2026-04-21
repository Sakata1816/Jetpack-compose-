package AnimeJ.presentation.screens.animeScreens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import AnimeJ.domain.model.server.EpisodeModel
import AnimeJ.presentation.viewModel.server.EpisodeListViewModel


@Composable
fun AnimeEpisodesList(animeId:Int?,viewModel: EpisodeListViewModel=hiltViewModel()){
    val state by viewModel.state.collectAsState()
    val listState = rememberLazyListState()


    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.episode) {episode->
                EpisodeDigit(episode)
            }
            item{
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

        LaunchedEffect(listState,animeId) {
            animeId?.let {
                viewModel.loadAnimeEpisodes(it) // первая загрузка
            }

            snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
                .collect { index ->
                    val lastIndex = state.episode.lastIndex
                    if (index != null && index >= lastIndex - 5) {
                        animeId?.let { viewModel.loadAnimeEpisodes(it) }
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
fun EpisodeDigit(episode: EpisodeModel){
    Text(text = "${episode.id} серия",
        fontSize = 24.sp)
}