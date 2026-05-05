package AnimeJ.data.repository.profile

import AnimeJ.data.source.auth.AuthDataSource
import AnimeJ.data.source.local.UserLocalDataSource
import AnimeJ.data.source.profile.FavoriteDataSource
import AnimeJ.domain.model.profile.FavoriteAnimeModel
import AnimeJ.domain.repository.profile.FavoriteRepository
import AnimeJ.mapper.animeLocalMapper.toDomain
import AnimeJ.mapper.animeLocalMapper.toEntity
import AnimeJ.mapper.animeProfileMapper.toDto
import AnimeJ.mapper.animeProfileMapper.toEntity
import AnimeJ.presentation.screens.components.AnimeStatus

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.collections.map

class FavoriteRepositoryImpl @Inject constructor(
   private val dataSource: FavoriteDataSource,
    private val local: UserLocalDataSource,
    private val auth: AuthDataSource
): FavoriteRepository{

    private val currentUserId get() = auth.getCurrentUser()?.uid
        ?: throw IllegalStateException("User not logged in")

    override fun getFavorites(query: String): Flow<List<FavoriteAnimeModel>> {
        return local.getAllAnime(query,currentUserId).map { list ->
            list.map { it.toDomain() }
        }
    }



    override suspend fun syncFromFirestore(): Result<Unit> {
        return try {
            val remoteList = dataSource.fetchAll()

            if (remoteList == null) {
                return Result.failure(Exception("Remote data is null"))
            }

            local.syncAll(remoteList.map { it.toEntity (currentUserId) },currentUserId)
            Result.success(Unit)
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun addAnime(anime: FavoriteAnimeModel): Result<Unit> {
        return try {
           dataSource.addAnime(anime.toDto())
            local.upsertAnime(anime.toEntity(currentUserId))
            Result.success(Unit)
        }catch (e: Exception){
            Result.failure(Exception("Failed to add anime", e))
        }
    }

    override suspend fun deleteAnime(malId: Int): Result<Unit> {
        return try {
            dataSource.deleteAnime(malId)
            local.deleteById( malId, currentUserId)
            Result.success(Unit)
        }catch (e: Exception){
            Result.failure(Exception("Failed to delete anime", e))
        }
    }




    // ---------------- LOCAL ----------------


    override fun getAnimeByStatus(status: AnimeStatus,query: String): Flow<List<FavoriteAnimeModel>> =
        local.getAnimeByStatus(status,query,currentUserId).map { list ->
            list.map { it.toDomain() }
        }




}