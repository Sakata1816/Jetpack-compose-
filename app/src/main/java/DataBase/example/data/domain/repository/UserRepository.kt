package DataBase.example.data.domain.repository

import DataBase.example.data.domain.domainModel.User
import kotlinx.coroutines.flow.Flow
import java.net.IDN


interface UserRepository {
    fun getUsers(): Flow<List<User>>
    suspend fun addUser(user: User)
    suspend fun clearUsers(id: Int)
}
