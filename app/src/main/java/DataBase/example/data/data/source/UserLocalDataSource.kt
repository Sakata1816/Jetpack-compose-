package DataBase.example.data.data.source

import DataBase.example.data.data.local.dao.UserDao
import DataBase.example.data.data.local.entity.UserEntity
import javax.inject.Inject


//это по сути источник локальных данных, не более, тут не бывает сложной логики

class UserLocalDataSource  @Inject constructor(private val dao: UserDao) {
    fun getUsers() = dao.getUsers()
    suspend fun insertUser(user: UserEntity) = dao.insertUser(user)
    suspend fun updateUser(user: UserEntity) = dao.updateUser(user)
    suspend fun deleteUser(id: Int) = dao.deleteUser(id)
    fun getUserById(id: Int) = dao.getUserById(id)

}
