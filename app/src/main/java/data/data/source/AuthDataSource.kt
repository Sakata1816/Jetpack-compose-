package data.data.source

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthDataSource {

    private val auth = FirebaseAuth.getInstance()

    suspend fun login(email: String, password: String) =
        auth.signInWithEmailAndPassword(email, password).await()

    suspend fun register(email: String, password: String) =
        auth.createUserWithEmailAndPassword(email, password).await()

    fun getCurrentUser() = auth.currentUser

    fun logout() = auth.signOut()
}

