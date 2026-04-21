package AnimeJ.data.repository.theme

import AnimeJ.data.source.theme.ThemePreferences
import AnimeJ.presentation.state.theme.ThemeMode
import android.content.Context
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

// domain/repository/ThemeRepository.kt
interface ThemeRepository {
    val themeFlow: Flow<ThemeMode>
    suspend fun setTheme(mode: ThemeMode)
}

// data/repository/ThemeRepositoryImpl.kt
@Singleton
class ThemeRepositoryImpl @Inject constructor(
    private val preferences: ThemePreferences
) : ThemeRepository {

    override val themeFlow: Flow<ThemeMode> = preferences.themeFlow

    override suspend fun setTheme(mode: ThemeMode) {
        preferences.setTheme(mode)
    }
}