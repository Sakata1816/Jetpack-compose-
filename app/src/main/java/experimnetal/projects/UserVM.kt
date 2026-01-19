package experimnetal.projects

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class UserRepository(
    private val api: MyApi
) {
    suspend fun getUsers(): List<User> {
        return api.getPosts()
    }
}


class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            _uiState.value = UserUiState(isLoading = true)

            try {
                val users = repository.getUsers()
                _uiState.value = UserUiState(users = users)
            } catch (e: Exception) {
                _uiState.value = UserUiState(error = e.message)
            }
        }
    }
}
