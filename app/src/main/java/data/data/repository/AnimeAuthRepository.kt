package data.data.repository

import data.data.source.AuthDataSource
import javax.inject.Inject

class AnimeAuthRepository @Inject constructor(
    private val dataSource: AuthDataSource
) {

    fun login(email: String, password: String) =
        dataSource.login(email, password)

    fun register(email: String, password: String) =
        dataSource.register(email, password)

    fun getCurrentUser() = dataSource.getCurrentUser()

    fun logout() = dataSource.logout()
}