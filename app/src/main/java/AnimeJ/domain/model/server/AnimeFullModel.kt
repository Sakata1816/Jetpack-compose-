package AnimeJ.domain.model.server

data class AnimeFullModel(
    val data: AnimeFullDataModel
)

data class AnimeFullDataModel(
    val id: Int,
    val title: String,
    val titleEnglish: String?,
    val titleJapanese: String?,
    val synopsis: String?,
    val images: ImagesModel,

    val score: Double?,
    val rank: Int?,
    val popularity: Int?,
    val episodes: Int?,
    val status: String?,
    val year: Int?,
    val season: String?,

    val trailer: TrailerModel?,
    val genres: List<NameModel>,
    val studios: List<NameModel>,
    val producers: List<NameModel>
)

data class ImagesModel(
    val jpg: JpgModel
)

data class JpgModel(
    val imageUrl: String?,
    val largeImageUrl: String?
)

data class TrailerModel(
    val url: String?,
    val youtubeId: String?
)

data class NameModel(
    val id: Int,
    val name: String
)


