package data.data.auth.DTO

import data.presentation.screens.components.AnimeStatus

data class UserProfile(
    val uid: String="",         // совпадает с FirebaseAuth uid
    val email: String="",
    val username: String = "",
    val avatarUrl: String = "",
    val favorites: List<FavoriteAnimeDto> = emptyList()
)

data class FavoriteAnimeDto(
    val mal_id: Int=0,
    val title: String="",
    val imageUrl: String? = null,
    val score: Double? = null,
    val status: AnimeStatus = AnimeStatus.NONE
)