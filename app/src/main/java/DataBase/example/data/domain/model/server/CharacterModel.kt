package DataBase.example.data.domain.model.server

data class CharacterItemModel(
    val id: Int,
    val name: String,
    val role: String,
    val imageUrl: String?
)

data class CharactersResponseModel(val data: List<CharacterItemModel>)

