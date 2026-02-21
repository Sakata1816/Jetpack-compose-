package DataBase.example.data.domain.model.server


data class EpisodeDetailResponseModel(
    val data: EpisodeDetailModel
)




data class EpisodeDetailModel(
    val id: Int,
    val title: String?,
    val titleJapanese: String?,
    val titleRomanji: String?,
    val duration: Int?,
    val aired: String?,
    val isFiller: Boolean,
    val isRecap: Boolean,
    val synopsis: String?,
    val url: String?
)

