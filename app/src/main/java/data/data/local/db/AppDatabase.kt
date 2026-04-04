package data.data.local.db

import data.data.local.dao.FavoriteAnimeDao
import data.data.local.entity.FavoriteAnimeEntity
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import data.presentation.screens.components.AnimeStatus

@Database(entities = [FavoriteAnimeEntity::class],
    version = 2,
    exportSchema = false)
    @TypeConverters(AnimeStatusConverter::class)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun favouriteAnimeDao(): FavoriteAnimeDao
    }




val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {

        db.execSQL("""
            ALTER TABLE favorite_anime 
            ADD COLUMN status TEXT NOT NULL DEFAULT 'NONE'
        """)

        // на всякий случай фикс старых значений
        db.execSQL("""
            UPDATE favorite_anime 
            SET status = 'NONE' 
            WHERE status IS NULL OR status = ''
        """)
    }
}


class AnimeStatusConverter {

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
}
