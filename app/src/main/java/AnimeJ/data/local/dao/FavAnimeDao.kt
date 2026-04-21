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

    @Query("SELECT * FROM favorite_anime")
    fun getAll(): Flow<List<FavoriteAnimeEntity>>


    //NEW
    @Upsert
    suspend fun upsertAll(list: List<FavoriteAnimeEntity>)

    @Upsert
    suspend fun upsertAnime(entity: FavoriteAnimeEntity)

    @Transaction
    suspend fun syncAll(list: List<FavoriteAnimeEntity>) {
        deleteAll()
        upsertAll(list)
    }
//Exit

    @Query("DELETE FROM favorite_anime WHERE mal_id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM favorite_anime")
    suspend fun deleteAll()

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_anime WHERE mal_id = :id)")
    suspend fun isFavorite(id: Int): Boolean

    @Query("SELECT * FROM favorite_anime WHERE mal_id = :id LIMIT 1")
    fun getAnimeById(id: Int): Flow<FavoriteAnimeEntity?>

    @Query("SELECT * FROM favorite_anime WHERE title LIKE '%' || :query || '%'")
    fun searchAnime(query: String): Flow<List<FavoriteAnimeEntity>>

    @Query("SELECT * FROM favorite_anime WHERE status = :status")
    fun getAnimeByStatus(status: AnimeStatus): Flow<List<FavoriteAnimeEntity>>
}
