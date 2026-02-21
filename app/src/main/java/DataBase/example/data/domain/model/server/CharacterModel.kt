package DataBase.example.data.domain.model.server

data class CharacterModel(
    val id: Int,
    val name: String,
    val role: String,
    val imageUrl: String?
)

data class CharacterResponseModel(val data: List<CharacterModel>)

