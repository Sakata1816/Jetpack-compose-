package AnimeJ.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AnimeAuthRepository {

    suspend fun login(email: String, password: String): Result<FirebaseUser>

    suspend fun register(email: String, password: String): Result<FirebaseUser>

    fun getCurrentUser(): FirebaseUser?

    fun logout()

}