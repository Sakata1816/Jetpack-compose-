package data.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.auth.FirebaseUser
import data.data.source.AuthDataSource
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AnimeAuthRepository @Inject constructor(
    private val dataSource: AuthDataSource
) {

    suspend fun login(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = dataSource.login(email, password)
            Result.success(result.user!!)
        }catch (e: Exception){
            Log.e(TAG, "Login failed", e)
            Result.failure(mapError(e))
        }
    }


    suspend fun register(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = dataSource.register(email, password)
            Result.success(result.user!!)
        } catch (e: Exception) {
            Log.e(TAG, "register failed", e)
            Result.failure(mapError(e))
        }
    }

   fun getCurrentUser() = dataSource.getCurrentUser()

   fun logout() = dataSource.logout()

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