package data.mapper.animeServerMapper

import data.data.server.DTO.AnimeCharactersResponse
import data.data.server.DTO.CharacterItemDto
import data.domain.model.server.CharacterItemModel
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
        imageUrl = character.images?.jpg?.image_url
    )
}
