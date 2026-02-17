package DataBase.example.data.data.source

import DataBase.example.data.data.local.dao.FavoriteAnimeDao
import DataBase.example.data.data.local.entity.FavoriteAnimeEntity
import javax.inject.Inject


//это по сути источник локальных данных, не более, тут не бывает сложной логики

class UserLocalDataSource  @Inject constructor(private val dao: FavoriteAnimeDao) {
    suspend fun getAllAnime() = dao.getAll()
    suspend fun insertAnime(anime: FavoriteAnimeEntity) = dao.insertAnime(anime)
    suspend fun deleteAnime(anime: FavoriteAnimeEntity) = dao.deleteAnime(anime)
    suspend fun isFavorite  (anime: FavoriteAnimeEntity) = dao.isFavorite(anime.mal_id)


}
