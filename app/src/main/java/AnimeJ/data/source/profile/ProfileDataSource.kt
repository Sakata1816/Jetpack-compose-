package AnimeJ.data.source.profile

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import AnimeJ.data.remote.auth.DTO.UserProfileDto
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileDataSource @Inject constructor(
    private val db: FirebaseFirestore,
    private val storage: FirebaseStorage
) {
    private val usersCollection = db.collection("users")

    suspend fun getUser(uid: String) =
        usersCollection.document(uid)
            .get()
            .await() // возвращает DocumentSnapshot





    suspend fun createUser(profile: UserProfileDto) =
        usersCollection.document(profile.uid).set(profile).await()

    suspend fun updateUser(uid: String, updates: Map<String, String?>) =
        usersCollection.document(uid).update(updates).await()


    suspend fun uploadAvatar(uid: String, uri: Uri): String {
        Log.d("Storage", "uid: $uid")
        Log.d("Storage", "auth: ${FirebaseAuth.getInstance().currentUser?.uid}")

        val ref = storage.reference.child("avatars/$uid.jpg")
        val uploadTask = ref.putFile(uri).await()

        return uploadTask.storage.downloadUrl.await().toString()

    }

}