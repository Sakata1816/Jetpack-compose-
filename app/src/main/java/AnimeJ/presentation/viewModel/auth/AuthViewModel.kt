package AnimeJ.presentation.viewModel.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import AnimeJ.domain.repository.auth.AnimeAuthRepository
import AnimeJ.domain.repository.profile.ProfileRepository
import AnimeJ.presentation.state.auth.AuthUiState
import AnimeJ.presentation.navigation.authRoot.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AnimeAuthRepository,
    private val profileRepository: ProfileRepository
) : ViewModel() {

    // UI состояние
    var uiState by mutableStateOf<AuthUiState>(AuthUiState.Idle)
        private set

    // Глобальное состояние авторизации
    private val _authState = MutableStateFlow<AuthState>(AuthState.Authorized)
    val authState = _authState.asStateFlow()

    private val auth = FirebaseAuth.getInstance()

    init {
       observeAuthState()
    }

    protected fun observeAuthState(){
        viewModelScope.launch {
            repository.observeAuthState().collect {state->
                _authState.value=state
            }
        }
    }

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
                val user=result.getOrNull()
                _authState.value = AuthState.Authorized
            }
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            uiState = AuthUiState.Loading

            val result = repository.register(email, password)

            if (result.isSuccess) {
                val user = result.getOrNull()!!
                // Проверяем/создаём профиль
                profileRepository.ensureUserProfile(user.uid, user.email ?: "")

                _authState.value = AuthState.Authorized
                uiState = AuthUiState.Success // или RequireProfileCreation если хочешь отдельный экран
            } else {
                uiState = AuthUiState.Error(result.exceptionOrNull()?.message ?: "Ошибка")
            }
        }
    }


    fun logout() {
        viewModelScope.launch {
            repository.logout()
            _authState.value = AuthState.Unauthorized
            uiState = AuthUiState.Idle
        }
    }
}