package DataBase.example.data.data.repository

import DataBase.example.data.data.source.UserLocalDataSource
import DataBase.example.data.domain.model.User
import DataBase.example.data.domain.repository.UserRepository
import DataBase.example.data.mapper.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class UserRepositoRryImpl @Inject constructor(
    private val localDataSource: UserLocalDataSource
) : UserRepository {

    // Получаем данные для UI
    override fun getUsers(): Flow<List<User>> {
        return localDataSource.getUsers() // Flow<List<UserEntity>>
            .map { list -> list.map { it.toDomain() } } // Entity → Domain через Mapper
    }

    override suspend fun addUser(user: User) {
        localDataSource.insertUser(user.toEntity()) // Domain → Entity через Mapper
    }
    override suspend fun deleteUser(id: Int) {
        localDataSource.deleteUser(id)
    }
    override suspend fun updateUser(user: User) {
        localDataSource.updateUser(user.toEntity())
    }
    override fun getUserById(id: Int): Flow<User?> {
        return localDataSource.getUserById(id).map { it?.toDomain() }
    }


}
