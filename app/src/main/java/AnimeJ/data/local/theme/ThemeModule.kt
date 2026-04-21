package AnimeJ.data.local.theme

import AnimeJ.data.repository.theme.ThemeRepository
import AnimeJ.data.repository.theme.ThemeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// di/ThemeModule.kt
@Module
@InstallIn(SingletonComponent::class)
abstract class ThemeModule {

    @Binds
    @Singleton
    abstract fun bindThemeRepository(
        impl: ThemeRepositoryImpl
    ): ThemeRepository
}