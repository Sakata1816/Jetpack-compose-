package AnimeJ.presentation.viewModel.profile

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import AnimeJ.data.remote.auth.DTO.UserProfileDto
import AnimeJ.domain.model.profile.UserProfileModel
import AnimeJ.domain.repository.auth.AnimeAuthRepository
import AnimeJ.domain.repository.profile.ProfileRepository
import AnimeJ.mapper.animeProfileMapper.toDto
import AnimeJ.mapper.animeProfileMapper.toModel
import AnimeJ.presentation.navigation.authRoot.AuthState
import AnimeJ.presentation.state.profile.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository,
    private val authRepository: AnimeAuthRepository
) : ViewModel() {


    var uiState by mutableStateOf<ProfileUiState>(ProfileUiState.Idle)
        private set

    var isSaved by mutableStateOf(false)
        private set

    var profile by mutableStateOf<UserProfileModel?>(null)
        private set




    fun loadProfile() {
        val uid = authRepository.getCurrentUser()?.uid

        if (uid == null) {
            uiState = ProfileUiState.Error("Пользователь не авторизован")
            return
        }

        viewModelScope.launch {
            uiState = ProfileUiState.Loading

                val result = repository.getUser(uid).fold(
                    onSuccess = {respose->
                        if (respose != null) {
                            profile = respose.toModel()
                            uiState = ProfileUiState.Success
                        } else {
                            uiState = ProfileUiState.Error("Профиль не найден")
                        }
                    },
                    onFailure = {throwable ->
                        uiState = ProfileUiState.Error("Ошибка загрузки профиля")

                    }
                )
            }
        }


    fun createProfile(profile: UserProfileModel) {
        viewModelScope.launch {
            repository.createUser(profile.toDto())
            this@ProfileViewModel.profile = profile
        }
    }

    fun updateProfile(username: String, uri: Uri?) {
        viewModelScope.launch {
            val uid = authRepository.getCurrentUser()?.uid ?: return@launch
            uiState = ProfileUiState.Loading
            try {
                val avatarUrl = if (uri != null) {
                    repository.uploadAvatar(uid, uri).getOrThrow()
                } else {
                    profile?.avatarUrl
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

}