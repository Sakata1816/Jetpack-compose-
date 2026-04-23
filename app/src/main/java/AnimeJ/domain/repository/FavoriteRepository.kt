package AnimeJ.domain.repository

import AnimeJ.domain.model.profile.FavoriteAnimeModel
import AnimeJ.presentation.screens.components.AnimeStatus
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun getFavorites(query: String): Flow<List<FavoriteAnimeModel>>

    suspend fun addAnime(anime: FavoriteAnimeModel): Result<Unit>

    suspend fun syncFromFirestore(): Result<Unit>


    //LOCAL
    suspend fun deleteAnime(id: Int): Result<Unit>

    suspend fun isFavorite(id: Int): Boolean

    fun getAnimeByStatus(status: AnimeStatus,query: String): Flow<List<FavoriteAnimeModel>>
}