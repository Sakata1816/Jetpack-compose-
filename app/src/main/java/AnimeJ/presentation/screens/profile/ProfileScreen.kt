package AnimeJ.presentation.screens.profile

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
import AnimeJ.presentation.navigation.mainRoot.NavRoute
import AnimeJ.presentation.screens.components.ThemeSelector
import AnimeJ.presentation.state.auth.ProfileUiState
import AnimeJ.presentation.viewModel.auth.AuthViewModel
import AnimeJ.presentation.viewModel.profile.ProfileViewModel
import AnimeJ.presentation.viewModel.theme.ThemeViewModel
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState






@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel(),
    authViewModel: AuthViewModel=hiltViewModel(),
    themeViewModel: ThemeViewModel = hiltViewModel()
) {
    val themeMode by themeViewModel.themeMode.collectAsState()

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
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        nickname.firstOrNull()?.uppercase() ?: "U",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 32.sp
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // Ник
            Text(
                text = nickname,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
            )

            Spacer(Modifier.height(4.dp))

            // Email
            Text(
                text = email,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
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
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Выйти", color = Color.White)
            }
        }

        ThemeSelector(
            current = themeMode,
            onSelect = { themeViewModel.changeTheme(it) }
        )


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