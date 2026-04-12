package data.domain.repository

import data.data.auth.DTO.FavoriteAnimeDto
import data.domain.model.profile.FavoriteAnimeModel
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun getFavorites(): Flow<List<FavoriteAnimeModel>>

    suspend fun addAnime(anime: FavoriteAnimeModel): Result<Unit>

    suspend fun deleteAnime(id: Int): Result<Unit>
}