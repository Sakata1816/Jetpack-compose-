package AnimeJ.data.source.theme

import AnimeJ.presentation.state.theme.ThemeMode
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


// data/local/ThemePreferences.kt
@Singleton
class ThemePreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataStore by lazy { context.dataStore }

    val themeFlow: Flow<ThemeMode> = dataStore.data.map { prefs ->
        when (prefs[THEME_KEY]) {
            "light" -> ThemeMode.LIGHT
            "dark" -> ThemeMode.DARK
            else -> ThemeMode.SYSTEM
        }
    }

    suspend fun setTheme(mode: ThemeMode) {
        dataStore.edit { prefs ->
            prefs[THEME_KEY] = mode.name.lowercase()
        }
    }

    companion object {
        private val Context.dataStore by preferencesDataStore(name = "settings")
        private val THEME_KEY = stringPreferencesKey("theme_mode")
    }
}