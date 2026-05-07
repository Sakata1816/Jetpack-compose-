package AnimeJ.presentation.state.profile

import AnimeJ.domain.model.profile.UserProfileModel


sealed class ProfileUiState {
    object Idle : ProfileUiState()
    object Loading : ProfileUiState()
    object Success : ProfileUiState()
    data class Error(val message: String) : ProfileUiState()

}

data class ProfileUiState1(
    val loading: Boolean = false,
    val error: String? = null,
    val profile: UserProfileModel? = null
)