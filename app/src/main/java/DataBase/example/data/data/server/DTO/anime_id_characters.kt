package DataBase.example.data.data.server.DTO


data class AnimeCharactersResponse(val data: List<CharacterItemDto>)

data class CharacterItemDto(
    val character: CharacterDto,
    val role: String
)

data class CharacterDto(
    val mal_id: Int,
    val name: String,
    val images: CharacterImagesDto?
)

data class CharacterImagesDto(val jpg: CharacterJpgDto?)
data class CharacterJpgDto(val image_url: String?)

