package DataBase.example.data.viewModel

import DataBase.example.data.domain.model.UserState
import DataBase.example.data.domain.model.User
import DataBase.example.data.domain.repository.UserRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UserState())
    val state: StateFlow<UserState> = _state

    init {
        observeUsers() // сразу подписываемся на данные
    }

    private fun observeUsers() {
        viewModelScope.launch {
            repository.getUsers() // Flow<List<User>>
                .collect { users ->
                    _state.update { it.copy(users = users) }
                }
        }
    }

    fun addUser(name: String, email: String) {
        viewModelScope.launch {
            repository.addUser(User(id = 0, name = name, email = email))
        }
    }

    fun deleteUser(id: Int) {
        viewModelScope.launch {
            repository.deleteUser(id)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            repository.updateUser(user)
        }
    }

    fun getUserById(id: Int){
        viewModelScope.launch {
            repository.getUserById(id)
        }

    }

}