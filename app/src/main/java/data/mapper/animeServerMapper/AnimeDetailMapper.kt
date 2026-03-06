package data.mapper.animeServerMapper

import data.data.server.DTO.AnimeDetailDto
import data.data.server.DTO.AnimeDetailResponse
import data.data.server.DTO.AnimeResponse
import data.domain.model.server.AnimeDetailModel
import data.domain.model.server.AnimeDetailResponseModel
import data.domain.model.server.AnimeResponseModel


fun AnimeResponse.toModel(): AnimeResponseModel {
    return AnimeResponseModel(
        data = data.map { it.toModel() }
    )
}

fun AnimeDetailResponse.toModel(): AnimeDetailResponseModel {
    return AnimeDetailResponseModel(
        data = data.toModel()
    )
}

fun AnimeDetailDto.toModel(): AnimeDetailModel {
    return AnimeDetailModel(
        id = mal_id,
        title = title,
        titleEnglish = title_english,
        titleJapanese = title_japanese,
        synopsis = synopsis,

        images = images?.toModel(),

        score = score,
        rank = rank,
        popularity = popularity,
        episodes = episodes,
        status = status,
        year = year,
        season = season,

        trailer = trailer?.toModel(),

        producers = producers.map { it.toModel() },
        studios = studios.map { it.toModel() },
        genres = genres.map { it.toModel() }
    )
}
