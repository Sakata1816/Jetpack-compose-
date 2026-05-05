package AnimeJ.mapper.animeProfileMapper

import AnimeJ.data.remote.auth.DTO.UserProfileDto
import AnimeJ.domain.model.profile.FavoriteAnimeModel
import AnimeJ.domain.model.profile.UserProfileModel


fun UserProfileDto.toModel(): UserProfileModel{
    return UserProfileModel(
        uid = uid,
        email = email,
        username = username,
        avatarUrl = avatarUrl
    )
}

fun UserProfileModel.toDto(): UserProfileDto{
    return UserProfileDto(
        uid = uid,
        email = email,
        username = username,
        avatarUrl = avatarUrl
    )
}





