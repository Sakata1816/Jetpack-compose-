package data.data.source

import com.google.firebase.firestore.FirebaseFirestore
import data.data.auth.DTO.UserProfile
import kotlinx.coroutines.tasks.await

class ProfileDataSource {

    private val db = FirebaseFirestore.getInstance()
    private val usersCollection = db.collection("users")

    suspend fun getUser(uid: String) =
        usersCollection.document(uid).get().await() // возвращает DocumentSnapshot

    suspend fun createUser(profile: UserProfile) =
        usersCollection.document(profile.uid).set(profile).await()

    suspend fun updateUser(uid: String, updates: Map<String, Any>) =
        usersCollection.document(uid).update(updates).await()

}