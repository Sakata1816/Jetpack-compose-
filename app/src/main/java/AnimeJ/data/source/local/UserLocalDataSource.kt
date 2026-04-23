package AnimeJ.data.source.local

import AnimeJ.data.local.dao.FavoriteAnimeDao
import AnimeJ.data.local.entity.FavoriteAnimeEntity
import AnimeJ.presentation.screens.components.AnimeStatus
import javax.inject.Inject

class UserLocalDataSource  @Inject constructor(
    private val dao: FavoriteAnimeDao
) {
    fun getAllAnime(query: String) = dao.searchAnime(query)

    suspend fun syncAll(list: List<FavoriteAnimeEntity>) = dao.syncAll(list)
    suspend fun upsertAnime(anime: FavoriteAnimeEntity) = dao.upsertAnime(anime)
    suspend fun upsertAll(list: List<FavoriteAnimeEntity>) = dao.upsertAll(list)
    suspend fun deleteById(id: Int) = dao.deleteById(id)
    suspend fun deleteAll() = dao.deleteAll()

    suspend fun isFavorite  (id:Int) = dao.isFavorite(id)
    fun getAnimeByStatus(status: AnimeStatus, query: String) = dao.getAnimeByStatus(status,query)
}