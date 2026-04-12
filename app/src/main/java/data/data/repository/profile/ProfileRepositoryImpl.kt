package data.data.repository.profile

import android.net.Uri
import data.data.auth.DTO.UserProfile
import data.data.source.profile.ProfileDataSource
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


    suspend fun uploadAvatar(uid: String, uri: Uri): String {
        return dataSource.uploadAvatar(uid, uri)
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