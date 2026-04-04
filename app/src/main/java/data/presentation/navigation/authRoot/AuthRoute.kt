package data.presentation.navigation.authRoot

sealed class AuthState {
    object Authorized : AuthState()
    object Unauthorized : AuthState()
}