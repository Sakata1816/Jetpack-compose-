package AnimeJ.mapper.animeServerMapper

import AnimeJ.data.remote.jikan.DTO.AnimeDetailDto
import AnimeJ.data.remote.jikan.DTO.AnimeDetailResponse
import AnimeJ.domain.model.server.AnimeDetailModel
import AnimeJ.domain.model.server.AnimeDetailResponseModel


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
