package AnimeJ.presentation.viewModel.theme

import AnimeJ.data.repository.theme.ThemeRepository
import AnimeJ.presentation.state.theme.ThemeMode
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val repository: ThemeRepository // 👈 интерфейс, не impl
) : ViewModel() {

    val themeMode: StateFlow<ThemeMode> = repository.themeFlow
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            ThemeMode.SYSTEM
        )

    fun changeTheme(mode: ThemeMode) {
        viewModelScope.launch {
            repository.setTheme(mode)
        }
    }
}