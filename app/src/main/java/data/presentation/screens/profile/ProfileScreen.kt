package data.presentation.screens.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import data.presentation.navigation.mainRoot.NavRoute
import data.presentation.state.auth.AuthUiState
import data.presentation.state.auth.ProfileUiState
import data.presentation.viewModel.auth.AuthViewModel
import data.presentation.viewModel.profile.ProfileViewModel

/*
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }

    val profile = viewModel.profile
    val uiState = viewModel.uiState



    when (uiState) {
        ProfileUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is ProfileUiState.Error -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(uiState.message, color = Color.Red)
            }
        }

        else -> {
            if (profile == null) return

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // 🔹 Аватар
                if (profile.avatarUrl.isNotEmpty()) {
                    Image(
                        painter = rememberAsyncImagePainter(profile.avatarUrl),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(Color.Gray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            profile.username.firstOrNull()?.uppercase() ?: "U",
                            color = Color.White,
                            fontSize = 32.sp
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                // 🔹 Ник
                Text(
                    text = profile.username,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(4.dp))

                // 🔹 Email
                Text(
                    text = profile.email,
                    color = Color.Gray
                )

                Spacer(Modifier.height(24.dp))

                // 🔹 Изменить
                Button(
                    onClick = {navController.navigate(NavRoute.ChangeProfile.route)},
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Изменить профиль")
                }

                Spacer(Modifier.height(12.dp))

                // 🔥 Logout
                Button(
                    onClick = {
                        viewModel.logout()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    )
                ) {
                    Text("Выйти", color = Color.White)
                }
            }
        }
    }
}
*/



@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel(),
    authViewModel: AuthViewModel=hiltViewModel()
) {
    val profile = viewModel.profile
    val uiState = viewModel.uiState

    // Загружаем профиль один раз при открытии
    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }

    // Локальные состояния полей
    val nickname by remember(profile) { derivedStateOf { profile?.username ?: "" } }
    val email by remember(profile) { derivedStateOf { profile?.email ?: "" } }
    val avatarUrl by remember(profile) { derivedStateOf { profile?.avatarUrl ?: "" } }
    // Каркас экрана — не зависит от загрузки
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Если профиль ещё не загрузился — показываем прогресс
        if (uiState == ProfileUiState.Loading) {
            CircularProgressIndicator()
        } else {
            // Аватар
            if (avatarUrl.isNotEmpty()) {
                Image(
                    painter = rememberAsyncImagePainter(avatarUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        nickname.firstOrNull()?.uppercase() ?: "U",
                        color = Color.Black,
                        fontSize = 32.sp
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // Ник
            Text(
                text = nickname,
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
            )

            Spacer(Modifier.height(4.dp))

            // Email
            Text(
                text = email,
                fontSize = 28.sp,
                color = Color.Black
            )

            Spacer(Modifier.height(24.dp))

            // Изменить профиль
            Button(
                onClick = { navController.navigate(NavRoute.ChangeProfile.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Изменить профиль")
            }

            Spacer(Modifier.height(12.dp))

            // Logout
            Button(
                onClick = { authViewModel.logout() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Выйти", color = Color.White)
            }
        }
    }
}


@Composable
fun ProfileChangeScreen(
    navController: NavController,
    viewModel: ProfileViewModel=hiltViewModel() // или отдельный ProfileViewModel
) {

    val profile = viewModel.profile

    LaunchedEffect(Unit) {
        viewModel.resetState()
        viewModel.loadProfile()
    }

    // 👇 Используем side effect для навигации — key на isSaved
    LaunchedEffect(viewModel.isSaved) {
        if (viewModel.isSaved) {
            viewModel.resetState() // сбрасываем ПЕРЕД навигацией
            navController.popBackStack()
        }
    }

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var nickname by remember(profile) { mutableStateOf(profile?.username ?: "") }

    // Лаунчер для выбора фото
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            selectedImageUri = uri   // 👈 только для превью
        }
    }

    Column(modifier = Modifier.padding(all = 16.dp)) {
        val imageToShow = selectedImageUri ?: profile?.avatarUrl?.takeIf { it.isNotEmpty() }

        if (imageToShow != null) {
            Image(
                painter = rememberAsyncImagePainter(imageToShow),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .clickable{launcher.launch("image/*")}
            )
        } else {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
                    .clickable{launcher.launch("image/*")},
                contentAlignment = Alignment.Center
            ) {
                Text(
                    nickname.firstOrNull()?.uppercase() ?: "U",
                    color = Color.White,
                    fontSize = 32.sp
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        TextField(
            value = nickname,
            onValueChange = { nickname = it },
            label = { Text("Введите никнейм") }
        )

        Spacer(modifier = Modifier.height(16.dp))


        val uiState = viewModel.uiState
        val isLoading = uiState is ProfileUiState.Loading


        // Показываем ошибку если есть
        if (uiState is ProfileUiState.Error) {
            Text(
                text = uiState.message,
                color = Color.Red,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        /*Button(onClick = {
            selectedImageUri?.let { uri ->
                viewModel.updateProfile(nickname,uri)
            }
        }
        ) {
            Text("Сохранить профиль")
        }*/

        Button(
            onClick = {
                // 👇 Сохраняем ВСЕГДА — даже если фото не менялось
                viewModel.updateProfile(nickname, selectedImageUri)
            },
            enabled = !isLoading && nickname.isNotBlank()
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    strokeWidth = 2.dp,
                    color = Color.White
                )
            } else {
                Text("Сохранить профиль")
            }
        }

    }
}