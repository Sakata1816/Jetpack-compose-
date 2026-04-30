package AnimeJ.mapper.animeServerMapper

import AnimeJ.data.remote.jikan.DTO.AiredDto
import AnimeJ.data.remote.jikan.DTO.AnimeDetailDto
import AnimeJ.data.remote.jikan.DTO.AnimeDetailResponse
import AnimeJ.data.remote.jikan.DTO.DateParts
import AnimeJ.data.remote.jikan.DTO.PropDto
import AnimeJ.domain.model.server.AiredModel
import AnimeJ.domain.model.server.AnimeDetailModel
import AnimeJ.domain.model.server.AnimeDetailResponseModel
import AnimeJ.domain.model.server.DatePartsModel
import AnimeJ.domain.model.server.PropModel


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
        producers = producers?.map { it.toModel() } ?:emptyList(),
        studios = studios?.map { it.toModel() } ?:emptyList(),
        genres = genres?.map { it.toModel() } ?:emptyList(),
        trailer = trailer?.toModel(),
        duration = duration,
        rating = rating,
        type = type,
        source = source,
        aired = aired?.toModel(),
        members = members,
        favorites = favorites,
        scoredBy = scoredBy,
        themes = themes?.map { it.toModel() }?:emptyList(),
        demographics = demographics?.map { it.toModel() }?:emptyList()
    )
}

fun AiredDto.toModel(): AiredModel{
    return AiredModel(
        from=from,
        to=to,
        prop=prop?.toModel()
    )
}

fun PropDto.toModel(): PropModel{
    return PropModel(
      from = from?.toModel(),
        to = to?.toModel()
    )
}

fun DateParts.toModel(): DatePartsModel{
    return DatePartsModel(
        day = day,
        month = month,
        year = year
    )
}