package AnimeJ.mapper.animeLocalMapper

import AnimeJ.data.local.entity.FavoriteAnimeEntity
import AnimeJ.domain.model.profile.FavoriteAnimeModel
import AnimeJ.domain.model.server.AnimeDetailModel
import AnimeJ.presentation.screens.components.AnimeStatus


fun FavoriteAnimeEntity.toDomain(): FavoriteAnimeModel {
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

fun FavoriteAnimeModel.toEntity(): FavoriteAnimeEntity {
    return FavoriteAnimeEntity(
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


