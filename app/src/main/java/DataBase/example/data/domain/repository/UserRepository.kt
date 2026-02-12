package DataBase.example.data.domain.repository

import DataBase.example.data.domain.model.User
import kotlinx.coroutines.flow.Flow
import java.net.IDN


interface UserRepository {
    fun getUsers(): Flow<List<User>>
    suspend fun addUser(user: User)
    suspend fun deleteUser(id: Int)
    suspend fun updateUser(user: User)
    fun getUserById(id: Int): Flow<User?>
}
