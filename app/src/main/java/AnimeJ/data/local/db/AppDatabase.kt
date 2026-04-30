package AnimeJ.data.local.db

import AnimeJ.data.local.dao.FavoriteAnimeDao
import AnimeJ.data.local.entity.FavoriteAnimeEntity
import AnimeJ.domain.model.server.NameModel
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import AnimeJ.presentation.screens.components.AnimeStatus

@Database(entities = [FavoriteAnimeEntity::class],
    version = 4,
    exportSchema = false)
    @TypeConverters(Converter::class)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun favouriteAnimeDao(): FavoriteAnimeDao
    }






class Converter {

    @TypeConverter
    fun fromStatus(status: AnimeStatus?): String {
        return status?.name ?: AnimeStatus.NONE.name
    }

    @TypeConverter
    fun toStatus(value: String?): AnimeStatus {
        return try {
            AnimeStatus.valueOf(value ?: AnimeStatus.NONE.name)
        } catch (e: Exception) {
            AnimeStatus.NONE // 👈 защита от краша
        }
    }


    @TypeConverter
    fun fromGenres(list: List<NameModel>?): String? {
        return list?.joinToString(",") { it.name }
    }

    @TypeConverter
    fun toGenres(data: String?): List<NameModel>? {
        return data?.split(",")?.mapIndexed { index, item ->
            NameModel(index,item.trim())
        }
        }
    }



