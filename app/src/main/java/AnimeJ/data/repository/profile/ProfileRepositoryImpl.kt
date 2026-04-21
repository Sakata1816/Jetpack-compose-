package AnimeJ.data.repository.profile

import android.net.Uri
import AnimeJ.data.remote.auth.DTO.UserProfile
import AnimeJ.data.source.profile.ProfileDataSource
import androidx.core.os.requestProfiling
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val dataSource: ProfileDataSource
) {

    suspend fun getUser(uid: String): UserProfile? {
        val doc = dataSource.getUser(uid)
        return doc.toObject(UserProfile::class.java)
    }


    suspend fun createUser(profile: UserProfile) {
        dataSource.createUser(profile)
    }


    suspend fun updateProfile(uid: String, username: String, avatarUrl: String) {
        dataSource.updateUser(
            uid,
            mapOf(
                "username" to username,
                "avatarUrl" to avatarUrl
            )
        )
    }


    suspend fun uploadAvatar(uid: String, uri: Uri): String {
        return dataSource.uploadAvatar(uid, uri)
    }


    suspend fun ensureUserProfile(uid: String, email: String): UserProfile {
        val existingProfile = getUser(uid)
        return if (existingProfile != null) {
            existingProfile
        } else {
            val newProfile = UserProfile(uid = uid, email = email) // можно добавить дефолтные поля
            createUser(newProfile)
            newProfile
        }
    }

}