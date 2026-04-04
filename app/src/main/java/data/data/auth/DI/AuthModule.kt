package data.data.auth.DI

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.data.repository.AnimeAuthRepository
import data.data.source.AuthDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthDataSource(): AuthDataSource {
        return AuthDataSource()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        dataSource: AuthDataSource
    ): AnimeAuthRepository {
        return AnimeAuthRepository(dataSource)
    }
}