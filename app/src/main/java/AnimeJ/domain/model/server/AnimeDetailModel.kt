package AnimeJ.domain.model.server

import AnimeJ.data.remote.jikan.DTO.AiredDto
import AnimeJ.data.remote.jikan.DTO.NameDto


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
    val producers: List<NameModel>?,
    val studios: List<NameModel>?,
    val genres: List<NameModel>?,
    val trailer: TrailerModel?,
    val duration: String?,
    val rating: String?,
    val type: String?,
    val source: String?,
    val aired: AiredModel?,
    val members: Int?,
    val favorites: Int?,
    val scoredBy: Int?,
    val themes: List<NameModel>?,
    val demographics: List<NameModel>?
)

data class AiredModel(
    val from: String?,
    val to: String?,
    val prop: PropModel?
)

data class PropModel(
    val from: DatePartsModel?,
    val to: DatePartsModel?
)

data class DatePartsModel(
    val day: Int?,
    val month: Int?,
    val year: Int?
)

