package data.mapper.animeLocalMapper

import data.data.local.entity.FavoriteAnimeEntity
import data.domain.model.local.FavoriteAnimeModel
import data.domain.model.server.AnimeDetailModel
import data.domain.model.server.AnimeResponseModel
import data.screens.components.AnimeStatus


fun FavoriteAnimeEntity.toDomain(): FavoriteAnimeModel {
    return FavoriteAnimeModel(
        mal_id = mal_id,
        title = title,
        imageUrl = imageUrl ,
        score = score,
        status=status
    )
}

fun FavoriteAnimeModel.toEntity(): FavoriteAnimeEntity {
    return FavoriteAnimeEntity(
        mal_id = mal_id,
        title = title,
        imageUrl = imageUrl,
        score = score,
        status=status
    )
}

fun AnimeDetailModel.toLocal(status: AnimeStatus): FavoriteAnimeModel {
    return FavoriteAnimeModel(
        mal_id = id,
        title = title,
        imageUrl = images?.jpg?.largeImageUrl,
        score = score,
        status = status
    )
}
