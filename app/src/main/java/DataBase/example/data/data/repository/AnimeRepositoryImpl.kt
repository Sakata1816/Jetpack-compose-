package DataBase.example.data.data.repository

import DataBase.example.data.data.source.UserLocalDataSource
import DataBase.example.data.data.source.UserServerDataSource
import DataBase.example.data.domain.repository.AnimeRepository
import javax.inject.Inject


class AnimeRepositoryImpl
@Inject
constructor(val server: UserServerDataSource, val local: UserLocalDataSource) :
    AnimeRepository {

}