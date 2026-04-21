package AnimeJ.mapper.animeServerMapper

import AnimeJ.data.remote.jikan.DTO.AnimeFullDto
import AnimeJ.data.remote.jikan.DTO.AnimeFullResponse
import AnimeJ.data.remote.jikan.DTO.ImagesDto
import AnimeJ.data.remote.jikan.DTO.JpgDto
import AnimeJ.data.remote.jikan.DTO.NameDto
import AnimeJ.data.remote.jikan.DTO.TrailerDto
import AnimeJ.domain.model.server.AnimeFullDataModel
import AnimeJ.domain.model.server.AnimeFullModel
import AnimeJ.domain.model.server.ImagesModel
import AnimeJ.domain.model.server.JpgModel
import AnimeJ.domain.model.server.NameModel
import AnimeJ.domain.model.server.TrailerModel


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

