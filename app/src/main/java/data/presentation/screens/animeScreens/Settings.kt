package data.presentation.screens.animeScreens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import data.presentation.viewModel.profile.ProfileViewModel


@Composable
fun SettingsScreen(navController: NavController,
                   viewModel: ProfileViewModel= hiltViewModel()){
    viewModel.uiState

}


@Composable
fun LogoutButton( logout:()->Unit){
    Button(onClick = {logout()}) {
        Text("Logout")
    }
}