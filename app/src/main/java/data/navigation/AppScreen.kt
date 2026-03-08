package data.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun RootScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) {
        AppNavGraph(navController=navController, modifier = Modifier.padding(it))
    }

}






@Composable
fun BottomBar(navController: NavController) {

    NavigationBar {

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("Main")
            },
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Main") }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("FavouriteAnime")
            },
            icon = { Icon(Icons.Default.Favorite, null) },
            label = { Text("Favourite") }
        )

        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate("Settings")
            },
            icon = { Icon(Icons.Default.Settings, null) },
            label = { Text("Settings") }
        )
    }
}

