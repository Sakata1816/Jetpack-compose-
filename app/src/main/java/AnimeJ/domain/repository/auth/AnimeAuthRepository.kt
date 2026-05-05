package AnimeJ.domain.repository.auth

import AnimeJ.presentation.navigation.authRoot.AuthState
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AnimeAuthRepository {

    suspend fun observeAuthState(): Flow<AuthState>
    suspend fun login(email: String, password: String): Result<FirebaseUser>

    suspend fun register(email: String, password: String): Result<FirebaseUser>

    fun getCurrentUser(): FirebaseUser?

    fun logout()

}