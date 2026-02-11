package DataBase.example.data.local.dao

import DataBase.example.data.local.entity.UserEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

//DAO - data access object
// мобъект который будет взаимодействовать с бд


@Dao
interface UserDao{

    @Query("SELECT * FROM users")
    suspend fun getAllUsers():List<UserEntity>


    @Insert
    suspend fun insertUser(user: UserEntity)

}