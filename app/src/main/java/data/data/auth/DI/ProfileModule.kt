package data.data.auth.DI

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.data.repository.AnimeAuthRepository
import data.data.repository.ProfileRepositoryImpl
import data.data.source.AuthDataSource
import data.data.source.ProfileDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {

    @Provides
    @Singleton
    fun provideProfileDataSource(): ProfileDataSource {
        return ProfileDataSource()
    }

    @Provides
    @Singleton
    fun provideProfileRepository(
        dataSource: ProfileDataSource
    ): ProfileRepositoryImpl {
        return ProfileRepositoryImpl(dataSource)
    }
}