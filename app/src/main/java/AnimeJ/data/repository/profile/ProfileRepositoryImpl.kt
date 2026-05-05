package AnimeJ.data.repository.profile

import android.net.Uri
import AnimeJ.data.remote.auth.DTO.UserProfileDto
import AnimeJ.data.source.profile.ProfileDataSource
import AnimeJ.domain.repository.profile.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val dataSource: ProfileDataSource,
): ProfileRepository {

  override  suspend fun getUser(uid: String): Result<UserProfileDto?>  {
      return runCatching {
          val doc = dataSource.getUser(uid).toObject(UserProfileDto::class.java)
          doc
      }
    }



    override  suspend fun createUser(profile: UserProfileDto): Result<Unit> {
      return runCatching {
          dataSource.createUser(profile)
      }
    }


   override suspend fun updateProfile(uid: String, username: String, avatarUrl: String?): Result<Unit> {
       return runCatching {
           dataSource.updateUser(
               uid,
               mapOf(
                   "username" to username,
                   "avatarUrl" to avatarUrl
               )
           )
       }
    }


   override suspend fun uploadAvatar(uid: String, uri: Uri): Result<String>{
        return runCatching {
            dataSource.uploadAvatar(uid, uri)
        }
    }


   override suspend fun ensureUserProfile(uid: String, email: String): Result<UserProfileDto?> {
        return runCatching {
            val existingProfile = getUser(uid).getOrThrow()
            existingProfile?:run {
                val newProfile = UserProfileDto(uid = uid, email = email) // можно добавить дефолтные поля
                createUser(newProfile)
                newProfile
            }
            }
        }

    }

