package data.mapper.animeLocalMapper

import data.data.local.entity.FavoriteAnimeEntity
import data.domain.model.local.FavoriteAnimeModel


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
