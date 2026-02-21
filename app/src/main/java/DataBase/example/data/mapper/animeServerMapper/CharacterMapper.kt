package DataBase.example.data.mapper.animeServerMapper

import DataBase.example.data.data.server.DTO.AnimeCharactersResponse
import DataBase.example.data.data.server.DTO.CharacterItemDto
import DataBase.example.data.domain.model.server.CharacterModel

fun AnimeCharactersResponse.toModel(): List<CharacterModel> {
    return data.map { it.toModel() }
}

fun CharacterItemDto.toModel(): CharacterModel {
    return CharacterModel(
        id = character.mal_id,
        name = character.name,
        role = role,
        imageUrl = character.images?.jpg?.image_url
    )
}
