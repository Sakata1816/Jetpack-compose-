package AnimeJ.domain.repository

import AnimeJ.data.remote.auth.DTO.UserProfile
import android.net.Uri

interface ProfileRepository {

    suspend fun getUser(uid: String): UserProfile?

    suspend fun createUser(profile: UserProfile)

    suspend fun updateProfile(uid: String, username: String, avatarUrl: String)

    suspend fun uploadAvatar(uid: String, uri: Uri): String

    suspend fun ensureUserProfile(uid: String, email: String): UserProfile

}