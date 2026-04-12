package data.data.source.profile

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import data.data.auth.DTO.FavoriteAnimeDto
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FavoriteDataSource @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) {

    private fun favoritesRef() =
        firestore.collection("users")
            .document(auth.uid!!)
            .collection("favorites")

   /* fun getFavorites(): Flow<List<FavoriteAnimeDto>> = callbackFlow {
        val listener = favoritesRef()
            .addSnapshotListener { snapshot, error ->

                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val list = snapshot?.documents?.mapNotNull {
                    it.toObject(FavoriteAnimeDto::class.java)
                } ?: emptyList()

                trySend(list)
            }

        awaitClose { listener.remove() }
    }*/

    fun getFavorites(): Flow<List<FavoriteAnimeDto>> = callbackFlow {

        val user = auth.currentUser

        if (user == null) {
            trySend(emptyList())
            close()
            return@callbackFlow
        }

        val listener = firestore.collection("users")
            .document(user.uid)
            .collection("favorites")
            .addSnapshotListener { snapshot, error ->

                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val list = snapshot?.documents?.mapNotNull {
                    it.toObject(FavoriteAnimeDto::class.java)
                } ?: emptyList()

                trySend(list)
            }

        awaitClose { listener.remove() }
    }

    suspend fun addAnime(anime: FavoriteAnimeDto) {
        favoritesRef()
            .document(anime.mal_id.toString())
            .set(anime)
    }

    suspend fun deleteAnime(id: Int) {
        favoritesRef()
            .document(id.toString())
            .delete()
    }
}