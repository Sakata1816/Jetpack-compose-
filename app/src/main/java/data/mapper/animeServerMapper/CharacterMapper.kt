package data.mapper.animeServerMapper

import data.data.server.DTO.AnimeCharactersResponse
import data.data.server.DTO.CharacterImagesDto
import data.data.server.DTO.CharacterItemDto
import data.data.server.DTO.CharacterJpgDto
import data.data.server.DTO.CharacterWebpDto
import data.domain.model.server.CharacterImagesModel
import data.domain.model.server.CharacterItemModel
import data.domain.model.server.CharacterJpgModel
import data.domain.model.server.CharacterWebpModel
import data.domain.model.server.CharactersResponseModel


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
