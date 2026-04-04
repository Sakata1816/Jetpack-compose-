package data.presentation.viewModel.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.data.auth.DTO.UserProfile
import data.data.repository.AnimeAuthRepository
import data.data.repository.ProfileRepositoryImpl
import data.presentation.navigation.authRoot.AuthState
import data.presentation.state.auth.AuthUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepositoryImpl,
    private val authRepository: AnimeAuthRepository
) : ViewModel() {

    var uiState by mutableStateOf<AuthUiState>(AuthUiState.Idle)
        private set

    private val _authState = MutableStateFlow<AuthState>(
        if (authRepository.getCurrentUser() != null)
            AuthState.Authorized
        else
            AuthState.Unauthorized
    )
    val authState = _authState.asStateFlow()

    var profile by mutableStateOf<UserProfile?>(null)
        private set

    fun loadProfile() {
        val uid = authRepository.getCurrentUser()?.uid ?: return
        viewModelScope.launch {
            profile = repository.getUser(uid)
        }
    }

    fun createProfile(profile: UserProfile) {
        viewModelScope.launch {
            repository.createUser(profile)
            this@ProfileViewModel.profile = profile
        }
    }

    fun updateProfile(updates: Map<String, Any>) {
        val uid = profile?.uid ?: return
        viewModelScope.launch {
            repository.updateUser(uid, updates)
            // обновим локально сразу
            profile = profile?.copy(
                username = updates["username"] as? String ?: profile!!.username,
                avatarUrl = updates["avatarUrl"] as? String ?: profile!!.avatarUrl
            )
        }
    }

    fun logout() {
        authRepository.logout()
        _authState.value = AuthState.Unauthorized
        uiState = AuthUiState.Idle
    }}