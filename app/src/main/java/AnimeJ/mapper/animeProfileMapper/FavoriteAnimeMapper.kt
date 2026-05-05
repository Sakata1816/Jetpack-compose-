package AnimeJ.mapper.animeProfileMapper

import AnimeJ.data.local.entity.FavoriteAnimeEntity
import AnimeJ.data.remote.auth.DTO.FavoriteAnimeProfileDto
import AnimeJ.domain.model.profile.FavoriteAnimeModel
import AnimeJ.domain.model.server.AnimeDetailModel
import AnimeJ.domain.model.server.ImagesModel
import AnimeJ.domain.model.server.JpgModel
import AnimeJ.presentation.screens.components.AnimeStatus

fun FavoriteAnimeProfileDto.toModel(): FavoriteAnimeModel {
    return FavoriteAnimeModel(
        mal_id = mal_id,
        title = title,
        imageUrl = imageUrl ,
        score = score,
        episodes = episodes,
        rank = rank,
        members = members,
        type = type,
        rating = rating,
        genres = genres,
        status = status
    )
}

fun FavoriteAnimeModel.toDto(): FavoriteAnimeProfileDto{
    return FavoriteAnimeProfileDto(
        mal_id = mal_id,
        title = title,
        imageUrl = imageUrl ,
        score = score,
        episodes = episodes,
        rank = rank,
        members = members,
        type = type,
        rating = rating,
        genres = genres,
        status = status
    )
}

fun AnimeDetailModel.toUi(status: AnimeStatus): FavoriteAnimeModel{
    return FavoriteAnimeModel(
        mal_id = id,
        title = title,
        imageUrl = images?.jpg?.largeImageUrl,
        score = score,
        episodes = episodes,
        rank = rank,
        members = members,
        type = type,
        rating = rating,
        genres = genres,
        status = status
    )
}

fun FavoriteAnimeModel.toDetail(): AnimeDetailModel {
    return AnimeDetailModel(
        id = mal_id,
        title = title,
        titleEnglish = null,
        titleJapanese = null,
        synopsis = null,
        images = imageUrl?.let {
            ImagesModel(
                jpg = JpgModel(
                    imageUrl = it,
                    largeImageUrl = it,
                    )
            )
        },
        score = score,
        rank = rank,
        popularity = null,
        episodes = episodes,
        status = null,
        year = null,
        season = null,
        producers = emptyList(),
        studios = emptyList(),

        // 👇 ВОССТАНАВЛИВАЕМ СПИСОК
        genres = genres,


        trailer = null,
        duration = null,
        rating = rating,
        type = type,
        source = null,
        aired = null,
        members = members,
        favorites = null,
        scoredBy = null,
        themes = emptyList(),
        demographics = emptyList()
    )
}

fun FavoriteAnimeProfileDto.toEntity(userId: String) = FavoriteAnimeEntity(
    mal_id = mal_id,
    userId = userId,
    title = title,
    imageUrl = imageUrl ,
    score = score,
    episodes = episodes,
    rank = rank,
    members = members,
    type = type,
    rating = rating,
    genres = genres,
    status = status
)