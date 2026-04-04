package data.data.repository

import data.data.auth.DTO.UserProfile
import data.data.source.ProfileDataSource
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

    suspend fun updateUser(uid: String, updates: Map<String, Any>) {
        dataSource.updateUser(uid, updates)
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