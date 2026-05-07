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
import AnimeJ.presentation.state.profile.ProfileUiState1
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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

    val _state = MutableStateFlow(ProfileUiState1())
    val state = _state.asStateFlow()




    fun loadProfile() {
        val uid = authRepository.getCurrentUser()?.uid

        if (uid == null) {
            _state.update {it.copy(error = "Пользователь не авторизован") }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(loading = true) }

                val result = repository.getUser(uid).fold(
                    onSuccess = {respose->
                        if (respose != null) {
                            _state.update { it.copy(
                                loading = false,
                                error = null,
                                profile = respose.toModel()
                            ) }
                        } else {
                            _state.update { it.copy(error = "Профиль не найден") }
                        }
                    },
                    onFailure = {throwable ->
                        _state.update { it.copy(error = "Ошибка загрузки профиля") }
                    }
                )
            }
        }


    fun createProfile(profile: UserProfileModel) {
        viewModelScope.launch {
            repository.createUser(profile.toDto())
            this@ProfileViewModel._state.update { it.copy(profile=profile) }
        }
    }

    fun updateProfile(username: String, uri: Uri?) {
        viewModelScope.launch {
            val uid = authRepository.getCurrentUser()?.uid ?: return@launch
            _state.update { it.copy(loading = true) }
            try {
                val avatarUrl = if (uri != null) {
                    repository.uploadAvatar(uid, uri).getOrThrow()
                } else {
                    state.value.profile?.avatarUrl
                }
                repository.updateProfile(uid, username, avatarUrl)

                profile = profile?.copy(
                    username = username,
                    avatarUrl = avatarUrl
                )

                //change
                _state.update { it.copy(
                    profile= profile?.copy(username = username,
                    avatarUrl = avatarUrl),
                    loading = false)
                }
                isSaved = true   // 👈 ВАЖНО
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message?:"Ошибка обновления профиля") }
            }
        }
    }

    fun resetState() {
        uiState = ProfileUiState.Idle
        isSaved = false // 👈 ОБЯЗАТЕЛЬНО сбрасываем
    }

}