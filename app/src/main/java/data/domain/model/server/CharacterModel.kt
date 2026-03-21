package data.domain.model.server


data class CharactersResponseModel(val data: List<CharacterItemModel>)

data class CharacterItemModel(
    val id: Int,
    val name: String,
    val role: String,
    val images: CharacterImagesModel?
)


data class CharacterImagesModel(val jpg: CharacterJpgModel?,val webp: CharacterWebpModel?)

data class CharacterWebpModel(val image_url: String?)

data class CharacterJpgModel(val image_url: String?)
