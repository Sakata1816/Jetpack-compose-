package data.data.local.dao

import data.data.local.entity.FavoriteAnimeEntity
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteAnimeDao {

    @Query("SELECT * FROM favorite_anime")
    fun getAll(): Flow<List<FavoriteAnimeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: FavoriteAnimeEntity)

    @Delete
    suspend fun deleteAnime(anime: FavoriteAnimeEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_anime WHERE mal_id = :id)")
    suspend fun isFavorite(id: Int): Boolean

    @Query("SELECT * FROM favorite_anime WHERE title LIKE '%' || :query || '%'")
    fun searchAnime(query: String): Flow<List<FavoriteAnimeEntity>>
}
