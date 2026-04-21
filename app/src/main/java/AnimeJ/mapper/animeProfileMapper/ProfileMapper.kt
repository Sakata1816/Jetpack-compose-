package AnimeJ.mapper.animeProfileMapper

import AnimeJ.data.local.entity.FavoriteAnimeEntity
import AnimeJ.data.remote.auth.DTO.FavoriteAnimeProfileDto
import AnimeJ.domain.model.profile.FavoriteAnimeModel
import AnimeJ.domain.model.server.AnimeDetailModel
import AnimeJ.presentation.screens.components.AnimeStatus

fun FavoriteAnimeProfileDto.toModel(): FavoriteAnimeModel {
    return FavoriteAnimeModel(
        mal_id=mal_id,
        title=title,
        imageUrl=imageUrl,
        score=score,
        status=status
    )
}

fun FavoriteAnimeModel.toDto(): FavoriteAnimeProfileDto{
    return FavoriteAnimeProfileDto(
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

fun FavoriteAnimeProfileDto.toEntity() = FavoriteAnimeEntity(
    mal_id = mal_id,
    title = title,
    imageUrl = imageUrl,
    score = score,
    status = status
)