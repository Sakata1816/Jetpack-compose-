package data.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.networkexperience.Screens.GetPost
import com.example.networkexperience.Screens.PostsAndComments
import com.example.networkexperience.navRoutes.Navigator

@Composable
fun AppNavGraph(navController: NavHostController,modifier: Modifier) {
    NavHost(modifier = modifier, navController=navController, startDestination = NavRoute.Main.route) {
        composable(NavRoute.FavouriteAnime.route){

        }
        composable(NavRoute.Anime.route){

        }
        composable(NavRoute.Settings.route){

        }
    }
}