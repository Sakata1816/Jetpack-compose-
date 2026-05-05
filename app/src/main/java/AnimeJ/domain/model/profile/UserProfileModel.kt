package AnimeJ.domain.model.profile

import AnimeJ.data.remote.auth.DTO.FavoriteAnimeProfileDto


data class UserProfileModel(
    val uid: String,
    val email: String?,
    val username: String?,
    val avatarUrl: String?,
)
