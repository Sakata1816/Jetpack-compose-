package AnimeJ.mapper.animeServerMapper

import AnimeJ.data.remote.jikan.DTO.AnimeCharactersResponse
import AnimeJ.data.remote.jikan.DTO.CharacterImagesDto
import AnimeJ.data.remote.jikan.DTO.CharacterItemDto
import AnimeJ.data.remote.jikan.DTO.CharacterJpgDto
import AnimeJ.data.remote.jikan.DTO.CharacterWebpDto
import AnimeJ.domain.model.server.CharacterImagesModel
import AnimeJ.domain.model.server.CharacterItemModel
import AnimeJ.domain.model.server.CharacterJpgModel
import AnimeJ.domain.model.server.CharacterWebpModel
import AnimeJ.domain.model.server.CharactersResponseModel


fun AnimeCharactersResponse.toModel(): CharactersResponseModel {
    return CharactersResponseModel(
        data = data.map { it.toModel() }
    )
}

fun CharacterItemDto.toModel(): CharacterItemModel {
    return CharacterItemModel(
        id = character.mal_id,
        name = character.name,
        role = role,
        images = character.images?.toModel()
    )
}

fun CharacterImagesDto.toModel(): CharacterImagesModel{
    return CharacterImagesModel(
        jpg=jpg?.toModel(),
        webp=webp?.toModel()
    )
}

fun CharacterJpgDto.toModel(): CharacterJpgModel{
    return CharacterJpgModel(
        image_url=image_url
    )
}

fun CharacterWebpDto.toModel(): CharacterWebpModel{
    return CharacterWebpModel(
        image_url=image_url
    )
}
