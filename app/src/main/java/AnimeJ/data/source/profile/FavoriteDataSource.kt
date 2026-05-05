package AnimeJ.data.source.profile

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import AnimeJ.data.remote.auth.DTO.FavoriteAnimeProfileDto
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FavoriteDataSource @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) {

    private fun favoritesRef() =
        firestore.collection("users")
            .document(auth.uid!!)
            .collection("favorites")



    suspend fun fetchAll(): List<FavoriteAnimeProfileDto> {
        val user = auth.currentUser ?: return emptyList()
        return firestore.collection("users")
            .document(user.uid)
            .collection("favorites")
            .get()
            .await()
            .documents
            .mapNotNull { it.toObject(FavoriteAnimeProfileDto::class.java) }
    }


    suspend fun addAnime(anime: FavoriteAnimeProfileDto) {
        favoritesRef()
            .document(anime.mal_id.toString())
            .set(anime)
    }

    suspend fun deleteAnime(malId: Int) {
        favoritesRef()
            .document(malId.toString())
            .delete()
    }
}