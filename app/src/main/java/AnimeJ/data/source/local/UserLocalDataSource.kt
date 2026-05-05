package AnimeJ.data.source.local

import AnimeJ.data.local.dao.FavoriteAnimeDao
import AnimeJ.data.local.entity.FavoriteAnimeEntity
import AnimeJ.presentation.screens.components.AnimeStatus
import javax.inject.Inject

class UserLocalDataSource  @Inject constructor(
    private val dao: FavoriteAnimeDao
) {
    fun getAllAnime(query: String, userId: String) = dao.searchAnime(query,userId)

    suspend fun syncAll(list: List<FavoriteAnimeEntity>,userId: String) = dao.syncAll(list,userId)

    suspend fun upsertAnime(anime: FavoriteAnimeEntity) = dao.upsertAnime(anime)

    suspend fun deleteById(malId: Int, userId: String) = dao.deleteById(malId,userId)

    fun getAnimeByStatus(status: AnimeStatus, query: String,userId: String) = dao.getAnimeByStatus(status,query,userId)
}