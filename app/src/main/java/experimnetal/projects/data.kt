package experimnetal.projects

data class User(val userId:Int,val id:Int,val title: String,val body: String)

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val token: String
)


data class UserUiState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String? = null
)