package DataBase.example.data.mapper.animeLocalMapper

import DataBase.example.data.data.local.entity.FavoriteAnimeEntity
import DataBase.example.data.domain.model.local.FavoriteAnimeModel


fun FavoriteAnimeEntity.toDomain(): FavoriteAnimeModel {
    return FavoriteAnimeModel(
        mal_id = mal_id,
        title = title,
        imageUrl = imageUrl ,
        score = score
    )
}

fun FavoriteAnimeModel.toEntity(): FavoriteAnimeEntity {
    return FavoriteAnimeEntity(
        mal_id = mal_id,
        title = title,
        imageUrl = imageUrl,
        score = score
    )
}
