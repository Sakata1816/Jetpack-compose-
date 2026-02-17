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
)