package AnimeJ.data.remote.auth.DTO

import AnimeJ.data.remote.jikan.DTO.AiredDto
import AnimeJ.data.remote.jikan.DTO.AnimeDetailDto
import AnimeJ.data.remote.jikan.DTO.ImagesDto
import AnimeJ.data.remote.jikan.DTO.NameDto
import AnimeJ.data.remote.jikan.DTO.TrailerDto
import AnimeJ.domain.model.server.AnimeDetailModel
import AnimeJ.domain.model.server.NameModel
import AnimeJ.presentation.screens.components.AnimeStatus
import androidx.room.PrimaryKey

data class UserProfileDto(
    val uid: String="",         // совпадает с FirebaseAuth uid
    val email: String?="",
    val username: String? = "",
    val avatarUrl: String? = "",
    val favorites: List<FavoriteAnimeProfileDto>? = emptyList()
)


data class FavoriteAnimeProfileDto(
    val mal_id: Int = 0,
    val title: String = "",
    val imageUrl: String? = null,
    val score: Double? = null,
    val episodes: Int? = null,
    val rank: Int? = null,
    val members: Int? = null,
    val type: String? = null,
    val rating: String? = null,
    val genres: List<NameModel>? = null,
    val status: AnimeStatus = AnimeStatus.NONE
)

