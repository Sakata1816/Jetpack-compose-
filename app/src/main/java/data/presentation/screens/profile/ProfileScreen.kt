package data.presentation.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import data.presentation.viewModel.profile.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val profile = viewModel.profile

    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }

    if (profile == null) {
        Text("Loading...")
    } else {
        Column {
            Text("Email: ${profile.email}")
            Text("Username: ${profile.username}")
        }
    }
}