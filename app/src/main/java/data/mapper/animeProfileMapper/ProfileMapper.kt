package data.mapper.animeProfileMapper

import data.data.auth.DTO.FavoriteAnimeDto
import data.domain.model.profile.FavoriteAnimeModel
import data.domain.model.server.AnimeDetailModel
import data.presentation.screens.components.AnimeStatus

fun FavoriteAnimeDto.toModel(): FavoriteAnimeModel{
    return FavoriteAnimeModel(
        mal_id=mal_id,
        title=title,
        imageUrl=imageUrl,
        score=score,
        status=status
    )
}

fun FavoriteAnimeModel.toDto(): FavoriteAnimeDto{
    return FavoriteAnimeDto(
        mal_id=mal_id,
        title=title,
        imageUrl=imageUrl,
        score=score,
        status=status
    )
}

fun AnimeDetailModel.toUi(status: AnimeStatus): FavoriteAnimeModel{
    return FavoriteAnimeModel(
        mal_id = id,
        title = title,
        imageUrl = images?.jpg?.largeImageUrl,
        score = score,
        status = status

    )
}