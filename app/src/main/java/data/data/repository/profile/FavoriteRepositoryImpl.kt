package data.data.repository.profile

import data.data.auth.DTO.FavoriteAnimeDto
import data.data.source.local.UserLocalDataSource
import data.data.source.profile.FavoriteDataSource
import data.data.source.profile.ProfileDataSource
import data.domain.model.profile.FavoriteAnimeModel
import data.domain.repository.FavoriteRepository
import data.mapper.animeLocalMapper.toDomain
import data.mapper.animeProfileMapper.toDto
import data.mapper.animeProfileMapper.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
   private val dataSource: FavoriteDataSource
): FavoriteRepository{

    override fun getFavorites(): Flow<List<FavoriteAnimeModel>> {
        return dataSource.getFavorites().map { list->
            list.map { it.toModel() }
        }
    }

    override suspend fun addAnime(anime: FavoriteAnimeModel): Result<Unit> {
        return try {
           dataSource.addAnime(anime.toDto())
            Result.success(Unit)
        }catch (e: Exception){
            Result.failure(Exception("Failed to add anime", e))
        }
    }

    override suspend fun deleteAnime(id: Int): Result<Unit> {
        return try {
            dataSource.deleteAnime(id)
            Result.success(Unit)
        }catch (e: Exception){
            Result.failure(Exception("Failed to delete anime", e))
        }
    }
}