package data.data.local.dao

import data.data.local.entity.FavoriteAnimeEntity
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteAnimeDao {

    @Query("SELECT * FROM favorite_anime")
    suspend fun getAll(): List<FavoriteAnimeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: FavoriteAnimeEntity)

    @Delete
    suspend fun deleteAnime(anime: FavoriteAnimeEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_anime WHERE mal_id = :id)")
    suspend fun isFavorite(id: Int): Boolean
}
