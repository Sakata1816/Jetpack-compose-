package data.data.source

import com.google.firebase.auth.FirebaseAuth

class AuthDataSource {

    private val auth = FirebaseAuth.getInstance()

    fun login(email: String, password: String) =
        auth.signInWithEmailAndPassword(email, password)

    fun register(email: String, password: String) =
        auth.createUserWithEmailAndPassword(email, password)

    fun getCurrentUser() = auth.currentUser

    fun logout() = auth.signOut()
}