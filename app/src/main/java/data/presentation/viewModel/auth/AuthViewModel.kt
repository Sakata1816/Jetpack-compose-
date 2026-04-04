package data.presentation.viewModel.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.data.repository.AnimeAuthRepository
import data.presentation.state.auth.AuthUiState
import data.presentation.navigation.authRoot.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AnimeAuthRepository
) : ViewModel() {

    // UI состояние
    var uiState by mutableStateOf<AuthUiState>(AuthUiState.Idle)
        private set

    // Глобальное состояние авторизации
    private val _authState = MutableStateFlow<AuthState>(
        if (repository.getCurrentUser() != null)
            AuthState.Authorized
        else
            AuthState.Unauthorized
    )
    val authState = _authState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            uiState = AuthUiState.Loading

            val result = repository.login(email, password)

            uiState = if (result.isSuccess) {
                AuthUiState.Success
            } else {
                AuthUiState.Error(
                    result.exceptionOrNull()?.message ?: "Ошибка"
                )
            }

            if (result.isSuccess) {
                _authState.value = AuthState.Authorized
            }
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            uiState = AuthUiState.Loading

            val result = repository.register(email, password)

            uiState = if (result.isSuccess) {
                AuthUiState.Success
            } else {
                AuthUiState.Error(
                    result.exceptionOrNull()?.message ?: "Ошибка"
                )
            }

            if (result.isSuccess) {
                _authState.value = AuthState.Authorized
            }
        }
    }

    fun logout() {
        repository.logout()
        _authState.value = AuthState.Unauthorized
        uiState = AuthUiState.Idle
    }
}