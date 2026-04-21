package AnimeJ.data.local.db

import AnimeJ.data.local.dao.FavoriteAnimeDao
import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "app_db"
        )
            .addMigrations(MIGRATION_1_2) // 👈 ВОТ ТУТ
            .build()
    }

    @Provides
    fun provideUserDao(db: AppDatabase): FavoriteAnimeDao {
        return db.favouriteAnimeDao()
    }
}
