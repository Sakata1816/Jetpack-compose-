package DataBase.example.data.local.dao

import DataBase.example.data.local.entity.UserEntity
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

//DAO - data access object
// мобъект который будет взаимодействовать с бд


@Dao
interface UserDao{

    @Query("SELECT * FROM users")
    suspend fun getAllUsers():List<UserEntity>

    @Update
    suspend fun updateUser(user: UserEntity)


    //По сути:
    //
    //@Insert = Room сам генерирует SQL INSERT
    //
    //@Query("INSERT ...") = ты сам пишешь SQL вручную
    //
    //Результат один: добавляется строка в таблицу.
    @Insert
    suspend fun insertUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: Int): Flow<UserEntity?>
}