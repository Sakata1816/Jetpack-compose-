package AnimeJ.presentation.navigation.authRoot

sealed class AuthState {
    object Authorized : AuthState()
    object Unauthorized : AuthState()


}