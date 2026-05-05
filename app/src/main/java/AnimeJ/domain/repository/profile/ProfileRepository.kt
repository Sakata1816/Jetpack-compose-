package AnimeJ.domain.repository.profile

import AnimeJ.data.remote.auth.DTO.UserProfileDto
import AnimeJ.domain.model.profile.UserProfileModel
import android.net.Uri

interface ProfileRepository {

    suspend fun getUser(uid: String): Result<UserProfileDto?>

    suspend fun createUser(profile: UserProfileDto): Result<Unit>

    suspend fun updateProfile(uid: String, username: String, avatarUrl: String?): Result<Unit>

    suspend fun uploadAvatar(uid: String, uri: Uri): Result<String?>

    suspend fun ensureUserProfile(uid: String, email: String): Result<UserProfileDto?>



}