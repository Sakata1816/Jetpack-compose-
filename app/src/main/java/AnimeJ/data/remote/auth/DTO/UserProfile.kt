package AnimeJ.data.remote.auth.DTO

import AnimeJ.presentation.screens.components.AnimeStatus

data class UserProfile(
    val uid: String="",         // совпадает с FirebaseAuth uid
    val email: String="",
    val username: String = "",
    val avatarUrl: String = "",
    val favorites: List<FavoriteAnimeProfileDto> = emptyList()
)

data class FavoriteAnimeProfileDto(
    val mal_id: Int=0,
    val title: String="",
    val imageUrl: String? = null,
    val score: Double? = null,
    val status: AnimeStatus = AnimeStatus.NONE
)