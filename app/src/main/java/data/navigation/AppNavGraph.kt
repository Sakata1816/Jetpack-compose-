package data.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import data.screens.AnimeListScreen


@Composable
fun AppNavGraph(navController: NavHostController,modifier: Modifier) {
    NavHost(modifier = modifier, navController=navController, startDestination = NavRoute.Main.route) {
        composable(NavRoute.Main.route){
            AnimeListScreen()
        }

        composable(NavRoute.FavouriteAnime.route){

        }

        composable(NavRoute.Settings.route){

        }
    }
}