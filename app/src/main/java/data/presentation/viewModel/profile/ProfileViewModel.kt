package data.presentation.viewModel.profile

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.data.auth.DTO.UserProfile
import data.data.repository.auth.AnimeAuthRepository
import data.data.repository.profile.ProfileRepositoryImpl
import data.presentation.navigation.authRoot.AuthState
import data.presentation.state.auth.AuthUiState
import data.presentation.state.auth.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepositoryImpl,
    private val authRepository: AnimeAuthRepository
) : ViewModel() {


    var uiState by mutableStateOf<ProfileUiState>(ProfileUiState.Idle)
        private set

    var isSaved by mutableStateOf(false)
        private set

    var profile by mutableStateOf<UserProfile?>(null)
        private set

    private val _authState = MutableStateFlow<AuthState>(
        if (authRepository.getCurrentUser() != null)
            AuthState.Authorized
        else
            AuthState.Unauthorized
    )
    val authState = _authState.asStateFlow()



    fun loadProfile() {
        val uid = authRepository.getCurrentUser()?.uid

        if (uid == null) {
            uiState = ProfileUiState.Error("Пользователь не авторизован")
            return
        }

        viewModelScope.launch {
            uiState =
                ProfileUiState.Loading

            try {
                val user = repository.getUser(uid)

                if (user != null) {
                    profile = user
                    uiState = ProfileUiState.Success
                } else {
                    uiState = ProfileUiState.Error("Профиль не найден")
                }

            } catch (e: Exception) {
                Log.e("ProfileVM", "loadProfile error", e)
                uiState = ProfileUiState.Error("Ошибка загрузки профиля")
            }
        }
    }

    fun createProfile(profile: UserProfile) {
        viewModelScope.launch {
            repository.createUser(profile)
            this@ProfileViewModel.profile = profile
        }
    }

    fun updateProfile(username: String, uri: Uri?) {
        viewModelScope.launch {
            val uid = authRepository.getCurrentUser()?.uid ?: return@launch
            uiState = ProfileUiState.Loading
            try {
                val avatarUrl = if (uri != null) {
                    repository.uploadAvatar(uid, uri)
                } else {
                    profile?.avatarUrl ?: ""
                }
                repository.updateProfile(uid, username, avatarUrl)

                profile = profile?.copy(
                    username = username,
                    avatarUrl = avatarUrl
                )

                isSaved = true   // 👈 ВАЖНО
                uiState = ProfileUiState.Success
            } catch (e: Exception) {
                uiState = ProfileUiState.Error(e.message ?: "Ошибка обновления профиля")
            }
        }
    }
    fun resetState() {
        uiState = ProfileUiState.Idle
        isSaved = false // 👈 ОБЯЗАТЕЛЬНО сбрасываем
    }

    fun logout() {
        authRepository.logout()
        _authState.value = AuthState.Unauthorized
        uiState = ProfileUiState.Idle
    }}