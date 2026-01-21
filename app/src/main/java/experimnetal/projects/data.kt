package experimnetal.projects

data class Post(val userId:Int?,val id:Int?,val title: String?,val body: String?)

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val token: String
)


data class UserUiState(
    val isLoading: Boolean = false,
    val users: List<Post> = emptyList(),
    val error: String? = null
)