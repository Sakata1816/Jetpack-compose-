package data.mapper.animeServerMapper

import data.data.server.DTO.AnimeFullDto
import data.data.server.DTO.AnimeFullResponse
import data.data.server.DTO.ImagesDto
import data.data.server.DTO.JpgDto
import data.data.server.DTO.NameDto
import data.data.server.DTO.TrailerDto
import data.domain.model.server.AnimeFullDataModel
import data.domain.model.server.AnimeFullModel
import data.domain.model.server.ImagesModel
import data.domain.model.server.JpgModel
import data.domain.model.server.NameModel
import data.domain.model.server.TrailerModel


fun AnimeFullResponse.toModel(): AnimeFullModel {
    return AnimeFullModel(
        data = data.toModel()
    )
}

fun AnimeFullDto.toModel(): AnimeFullDataModel {
    return AnimeFullDataModel(
        id = mal_id,
        title = title,
        titleEnglish = title_english,
        titleJapanese = title_japanese,
        synopsis = synopsis,
        images = images.toModel(),

        score = score,
        rank = rank,
        popularity = popularity,
        episodes = episodes,
        status = status,
        year = year,
        season = season,

        trailer = trailer?.toModel(),
        genres = genres.map { it.toModel() },
        studios = studios.map { it.toModel() },
        producers = producers.map { it.toModel() }
    )
}

fun ImagesDto.toModel(): ImagesModel {
    return ImagesModel(
        jpg = jpg.toModel()
    )
}

fun JpgDto.toModel(): JpgModel {
    return JpgModel(
        imageUrl = image_url,
        largeImageUrl = large_image_url
    )
}

fun TrailerDto.toModel(): TrailerModel {
    return TrailerModel(
        url = url,
        youtubeId = youtube_id
    )
}

fun NameDto.toModel(): NameModel {
    return NameModel(
        id = mal_id,
        name = name
    )
}

