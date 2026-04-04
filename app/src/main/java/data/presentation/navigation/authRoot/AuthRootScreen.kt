package data.presentation.navigation.authRoot

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import data.presentation.navigation.mainRoot.MainRoot
import data.presentation.screens.auth.AuthScreen
import data.presentation.state.auth.AuthUiState
import data.presentation.viewModel.auth.AuthViewModel

@Composable
fun RootScreen(
    viewModel: AuthViewModel = hiltViewModel()
) {
    val authState by viewModel.authState.collectAsState()


    when (authState) {

        AuthState.Authorized -> {
            MainRoot()
        }

        AuthState.Unauthorized -> {
            AuthRoot()
        }
    }
}

@Composable
fun AuthRoot(
    viewModel: AuthViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            AuthScreen()
        }
    }
}