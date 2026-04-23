package AnimeJ.presentation.screens.animeScreens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import AnimeJ.presentation.navigation.mainRoot.NavRoute
import AnimeJ.presentation.screens.components.AnimeStatus
import AnimeJ.presentation.screens.components.StatusDropdown
import AnimeJ.presentation.viewModel.profile.FavoriteAnimeViewModel
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewModelScope
import coil.request.CachePolicy
import coil.request.ImageRequest
import kotlinx.coroutines.launch


@Composable
fun FavouriteAnimeScreen(
    navController: NavController,
    viewModel: FavoriteAnimeViewModel = hiltViewModel()
) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val list by viewModel.getFavoritesStatus.collectAsState()
    val scope = rememberCoroutineScope()


    val tabs = listOf(
        "Смотрю" to AnimeStatus.WATCHING,
        "Просмотрено" to AnimeStatus.COMPLETED,
        "Брошено" to AnimeStatus.DROPPED,
        "Запланировано" to AnimeStatus.PLAN
    )

    val pagerState = rememberPagerState { tabs.size }

    // 👇 Когда свайпаем — меняем статус
    LaunchedEffect(pagerState.currentPage) {
        viewModel.setStatus(tabs[pagerState.currentPage].second)
    }

    Column(modifier = Modifier.fillMaxSize()) {

        // 🔍 SEARCH
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { viewModel.setSearch(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            placeholder = { Text("Поиск...") },
            singleLine = true,
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { viewModel.setSearch("") }) {
                        Icon(Icons.Default.Clear, contentDescription = null)
                    }
                }
            }
        )

        // 📑 TABS — при нажатии скроллим pager
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 16.dp,
            divider = {}
        ) {
            tabs.forEachIndexed { index, (title, _) ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        // 👇 При нажатии на таб — анимированно скроллим
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.labelLarge
                        )
                    },
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        HorizontalDivider()

        // 👇 Горизонтальный пейджер
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                if (list.isEmpty()) {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.BookmarkBorder,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(Modifier.height(12.dp))
                        Text(
                            text = "Список пуст",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        items(list) { anime ->
                            FavouriteAnime(
                                anime = anime,
                                onClick = { id ->
                                    navController.navigate(NavRoute.AnimeDetails.createRoute(id))
                                },
                                onStatusChange = { newStatus ->
                                    viewModel.changeStatus(anime, newStatus)
                                },
                                currentStatus = anime.status
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun FavouriteAnime(anime: AnimeJ.domain.model.profile.FavoriteAnimeModel,
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
            model = ImageRequest.Builder(LocalContext.current)
                .data(anime.imageUrl)
                .diskCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build(),            contentDescription = null,
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


/*
fun FavScreen.toStatus(): AnimeStatus {
    return when (this) {
        FavScreen.Planned -> AnimeStatus.PLAN
        FavScreen.Watching -> AnimeStatus.WATCHING
        FavScreen.Completed -> AnimeStatus.COMPLETED
        FavScreen.Dropped -> AnimeStatus.DROPPED
    }
}
*/
