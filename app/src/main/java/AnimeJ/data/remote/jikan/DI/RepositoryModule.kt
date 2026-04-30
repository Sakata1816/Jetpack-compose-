package AnimeJ.data.remote.jikan.DI

import AnimeJ.data.repository.auth.AnimeAuthRepositoryImpl
import AnimeJ.data.repository.profile.FavoriteRepositoryImpl
import AnimeJ.data.repository.profile.ProfileRepositoryImpl
import AnimeJ.data.repository.server.AnimeRepositoryImpl
import AnimeJ.domain.repository.AnimeAuthRepository
import AnimeJ.domain.repository.AnimeRepository
import AnimeJ.domain.repository.FavoriteRepository
import AnimeJ.domain.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepo(
        impl: AnimeRepositoryImpl
    ): AnimeRepository

    @Binds
    @Singleton
    abstract fun bindAnimeAuthRepository(
        impl: AnimeAuthRepositoryImpl  // твоя реализация
    ): AnimeAuthRepository

    @Binds
    @Singleton
    abstract fun bindProfileRepository(
        impl: ProfileRepositoryImpl  // твоя реализация
    ): ProfileRepository

    @Binds
    @Singleton
    abstract fun bindFavoriteRepository(
        impl: FavoriteRepositoryImpl
    ): FavoriteRepository
}