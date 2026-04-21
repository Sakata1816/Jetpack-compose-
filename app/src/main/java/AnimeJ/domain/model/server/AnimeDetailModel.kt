package AnimeJ.domain.model.server



data class AnimeDetailResponseModel(
    val data: AnimeDetailModel
)

data class AnimeDetailModel(
    val id: Int,
    val title: String,
    val titleEnglish: String?,
    val titleJapanese: String?,
    val synopsis: String?,
    val images: ImagesModel?,
    val score: Double?,
    val rank: Int?,
    val popularity: Int?,
    val episodes: Int?,
    val status: String?,
    val year: Int?,
    val season: String?,
    val producers: List<NameModel>,
    val studios: List<NameModel>,
    val genres: List<NameModel>,
    val trailer: TrailerModel?
)

