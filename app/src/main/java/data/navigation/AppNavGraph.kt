package data.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import data.screens.AnimeDetailsScreen
import data.screens.AnimeEpisodesList
import data.screens.AnimeListScreen
import data.screens.EpisodeDigit


@Composable
fun AppNavGraph(navController: NavHostController,modifier: Modifier) {
    NavHost(modifier = modifier, navController=navController, startDestination = NavRoute.Main.route) {
        composable(NavRoute.Main.route){
            AnimeListScreen(navController)
        }
        composable(
            route = NavRoute.AnimeDetails.route
        ) { backStackEntry ->

            val animeId =
                backStackEntry.arguments?.getString("animeId")?.toInt()

            AnimeDetailsScreen(animeId,navController)
        }

        composable(NavRoute.Episodes.route){ backStackEntry->
            val animeId =
                backStackEntry.arguments?.getString("animeId")?.toInt()
            AnimeEpisodesList(animeId)


        }

        composable(NavRoute.FavouriteAnime.route){

        }

        composable(NavRoute.Settings.route){

        }
    }
}