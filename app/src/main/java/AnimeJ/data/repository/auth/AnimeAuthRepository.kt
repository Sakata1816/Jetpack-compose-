package AnimeJ.data.repository.auth

import android.content.ContentValues
import android.util.Log
import com.google.firebase.auth.FirebaseUser
import AnimeJ.data.source.auth.AuthDataSource
import AnimeJ.domain.repository.AnimeAuthRepository
import javax.inject.Inject

class AnimeAuthRepositoryImpl @Inject constructor(
    private val dataSource: AuthDataSource
): AnimeAuthRepository {

    override suspend fun login(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = dataSource.login(email, password)
            Result.success(result.user!!)
        }catch (e: Exception){
            Log.e(ContentValues.TAG, "Login failed", e)
            Result.failure(mapError(e))
        }
    }


   override suspend fun register(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = dataSource.register(email, password)
            Result.success(result.user!!)
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "register failed", e)
            Result.failure(mapError(e))
        }
    }

  override fun getCurrentUser() = dataSource.getCurrentUser()

  override fun logout() = dataSource.logout()

    private fun mapError(e: Exception): Exception {
        return Exception(
            when (e.message) {
                "The email address is badly formatted." -> "Неверный email"
                "The password is invalid or the user does not have a password." -> "Неверный пароль"
                else -> "Ошибка авторизации"
            }
        )
    }
}