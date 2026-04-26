package AnimeJ.presentation.screens.animeScreens

import androidx.compose.foundation.layout.Arrangement
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
import coil.compose.AsyncImage
import AnimeJ.domain.model.server.AnimeDetailModel
import AnimeJ.domain.model.server.CharacterItemModel
import AnimeJ.presentation.navigation.mainRoot.NavRoute
import AnimeJ.presentation.screens.components.BackButton
import AnimeJ.presentation.viewModel.server.AnimeDetailViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)

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
                    AnimeDetailsContent(modifier = Modifier.fillMaxSize(), anime=anime, characters = state.characters, onClick = { id->
                        navController.navigate(NavRoute.Episodes.createRoute(id))
                    },
                        onBack = {navController.popBackStack()})
                }
            }

        }
        }






@Composable
fun AnimeDetailsContent(
    modifier: Modifier,
    anime: AnimeDetailModel,
    characters: List<CharacterItemModel>,
    onClick:(Int)-> Unit,
    onBack: () -> Unit
) {
    var currentScreen by remember { mutableStateOf(DetScreen.Description) }

    val tabs = listOf("Descrptions","Characters")
    val lists = listOf(anime,characters)

    val pagerState = rememberPagerState { tabs.size }
    val scope = rememberCoroutineScope()



    LazyColumn(
        modifier = modifier
    ) {
        item {
            AnimeHeader(
                imageUrl = anime.images?.jpg?.largeImageUrl
                    ?: anime.images?.jpg?.imageUrl.orEmpty(),
                onBack
            )
        }


        item {
            Spacer(modifier = Modifier.height(12.dp))

            // Название
            Text(
                text = anime.title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        item {
            Spacer(Modifier.height(12.dp))

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
        }

        item {
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

        }


        stickyHeader {
            Surface( // 👈 Surface чтобы фон не был прозрачным при скролле
                color = MaterialTheme.colorScheme.background
            ) {
                // 📑 TABS — при нажатии скроллим pager
                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    divider = {}
                ) {
                    tabs.forEachIndexed { index, title ->
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
            }
        }

        item {
            // 👇 Горизонтальный пейджер
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight() // 👈 высота по контенту
            ) { page ->

                Box(modifier = Modifier.fillMaxSize()) {
                    when (page) {
                        0  -> {
                            // Описание
                            Column(
                                modifier = Modifier.fillMaxSize()
                                    .padding(16.dp)
                            ) {
                                AnimeDownInfo(anime)
                            }
                        }
                        1 -> {
                            // Персонажи
                            if (characters.isEmpty()) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Персонажи не найдены",
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            } else {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                ) {
                                    characters.forEach{ character ->
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 16.dp, vertical = 8.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            AsyncImage(
                                                model = character.images?.jpg?.image_url
                                                    ?: character.images?.webp?.image_url,
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(64.dp)
                                                    .clip(RoundedCornerShape(8.dp)),
                                                contentScale = ContentScale.Crop
                                            )
                                            Spacer(Modifier.width(12.dp))
                                            Column {
                                                Text(
                                                    text = character.name,
                                                    style = MaterialTheme.typography.titleSmall,
                                                    fontWeight = FontWeight.SemiBold
                                                )
                                                Spacer(Modifier.height(4.dp))
                                                Text(
                                                    text = character.role,
                                                    style = MaterialTheme.typography.bodySmall,
                                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                                )
                                            }
                                        }
                                        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
        }
        }





@Composable
fun AnimeHeader(imageUrl: String,
                onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {

        // 🔹 фон blur
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
                .blur(25.dp)
        )

        // 🔹 затемнение
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.8f), // верх
                            Color.Transparent,              // центр
                            Color.Black.copy(alpha = 0.9f)  // низ
                        )
                    )
                )        )

        // 🔹 карточка
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .width(200.dp)
                .height(280.dp)
                .clip(RoundedCornerShape(16.dp)),
        )

        BackButton(onBack)
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



enum class DetScreen {
    Description, Characters
}
