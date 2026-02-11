package DataBase.example.data.local.db

import DataBase.example.data.local.dao.UserDao
import DataBase.example.data.local.entity.UserEntity
import androidx.room.Database
import androidx.room.RoomDatabase

@Database([UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}