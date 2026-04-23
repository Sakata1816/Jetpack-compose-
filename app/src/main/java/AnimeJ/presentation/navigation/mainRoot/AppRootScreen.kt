package AnimeJ.presentation.navigation.mainRoot


import AnimeJ.presentation.screens.components.BackButton
import AnimeJ.presentation.state.theme.ThemeMode
import AnimeJ.presentation.viewModel.theme.ThemeViewModel
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun MainRoot(
) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomBarRoutes = listOf(
        NavRoute.Main.route,
        NavRoute.FavouriteAnime.route,
        NavRoute.Settings.route
    )

    val backButtonRoutes = listOf(
        NavRoute.AnimeDetails.route
    )

    val showBackButton = backButtonRoutes.any { route ->
        currentRoute == route || currentRoute?.startsWith("anime_details/") == true
    }

    val showBar=bottomBarRoutes.any{route->
        currentRoute == route || currentRoute?.startsWith("settings/") == true
    }




    Scaffold(
        bottomBar = {
            if (showBar){
                BottomBar(navController, currentRoute)
            }
        },
        topBar = {

        }
    ) {
        AppNavGraph(navController=navController, modifier = Modifier.padding(it))
    }

}







@Composable
fun BottomBar(
    navController: NavController,
    currentRoute: String?) {

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceContainer
    ){

        NavigationBarItem(
            selected = currentRoute == NavRoute.Main.route,
            onClick = {
                navController.navigate("Main"){
                    popUpTo(NavRoute.Main.route){
                        saveState = true
                        inclusive = false
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                      },
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Main") }
        )

        NavigationBarItem(
            selected = currentRoute == NavRoute.FavouriteAnime.route,
            onClick = {
                navController.navigate("FavouriteAnime"){
                    popUpTo(NavRoute.Main.route) {  // по строке, не по ID
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = { Icon(Icons.Default.Favorite, null) },
            label = { Text("Favourite") }
        )

        NavigationBarItem(
            selected = currentRoute == NavRoute.Settings.route,
            onClick = {
                navController.navigate(NavRoute.Settings.route){
                    popUpTo(NavRoute.Main.route){
                        saveState=true
                    }
                    launchSingleTop=true
                    restoreState=true
                }
            },
            icon = { Icon(Icons.Default.Settings, null) },
            label = { Text("Settings") }
        )
    }
}

