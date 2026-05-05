package AnimeJ.data.local.dao

import AnimeJ.data.local.entity.FavoriteAnimeEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import AnimeJ.presentation.screens.components.AnimeStatus
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteAnimeDao {

    @Query("SELECT * FROM favorite_anime WHERE userId = :userId")
    fun getAll(userId: String): Flow<List<FavoriteAnimeEntity>>


    //NEW
    @Upsert
    suspend fun upsertAll(list: List<FavoriteAnimeEntity>)

    @Upsert
    suspend fun upsertAnime(entity: FavoriteAnimeEntity)

    @Transaction
    suspend fun syncAll(list: List<FavoriteAnimeEntity>,userId: String) {
        deleteAll(userId)
        upsertAll(list)
    }
//Exit

    @Query("DELETE FROM favorite_anime WHERE mal_id = :malId AND userId = :userId")
    suspend fun deleteById(malId: Int, userId: String)

    @Query("DELETE FROM favorite_anime WHERE userId = :userId")
    suspend fun deleteAll(userId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_anime WHERE mal_id = :id)")
    suspend fun isFavorite(id: Int): Boolean

    @Query("SELECT * FROM favorite_anime WHERE mal_id = :malId AND userId = :userId LIMIT 1")
    fun getAnimeById(malId: Int, userId: String): Flow<FavoriteAnimeEntity?>

    @Query("SELECT * FROM favorite_anime WHERE title LIKE '%' || :query || '%' AND userId = :userId")
    fun searchAnime(query: String,userId: String): Flow<List<FavoriteAnimeEntity>>

    @Query("""
    SELECT * FROM favorite_anime 
    WHERE status = :status 
    AND userId = :userId
    AND LOWER(title) LIKE '%' || LOWER(:query) || '%'
""")
    fun getAnimeByStatus(status: AnimeStatus, query: String,userId: String): Flow<List<FavoriteAnimeEntity>>
}
