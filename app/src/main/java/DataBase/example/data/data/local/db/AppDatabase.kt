package DataBase.example.data.data.local.db

import DataBase.example.data.data.local.dao.FavoriteAnimeDao
import DataBase.example.data.data.local.dao.UserDao
import DataBase.example.data.data.local.entity.FavoriteAnimeEntity
import DataBase.example.data.data.local.entity.UserEntity
import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(entities = [FavoriteAnimeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouriteAnimeDao(): FavoriteAnimeDao
}

