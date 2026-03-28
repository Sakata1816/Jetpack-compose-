package data.data.source

import data.data.local.dao.FavoriteAnimeDao
import data.data.local.entity.FavoriteAnimeEntity
import data.screens.components.AnimeStatus
import javax.inject.Inject


//это по сути источник локальных данных, не более, тут не бывает сложной логики

class UserLocalDataSource  @Inject constructor(private val dao: FavoriteAnimeDao) {
    fun getAllAnime(query: String) = dao.searchAnime(query)
    suspend fun insertAnime(anime: FavoriteAnimeEntity) = dao.insertAnime(anime)
    suspend fun deleteAnime(id: Int) = dao.deleteAnime(id)
    suspend fun isFavorite  (id:Int) = dao.isFavorite(id)
    fun searchAnime(query: String) = dao.searchAnime(query)
    fun getAnimeByStatus(status: AnimeStatus) = dao.getAnimeByStatus(status)
}
