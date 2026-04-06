package data.presentation.navigation.mainRoot

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import data.presentation.screens.animeScreens.AnimeDetailsScreen
import data.presentation.screens.animeScreens.AnimeEpisodesList
import data.presentation.screens.animeScreens.AnimeListScreen
import data.presentation.screens.animeScreens.FavouriteAnimeScreen
import data.presentation.screens.profile.ProfileChangeScreen
import data.presentation.screens.profile.ProfileScreen


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
            FavouriteAnimeScreen(navController)
        }

        composable(NavRoute.Settings.route){
            ProfileScreen(navController)
        }

        composable(NavRoute.ChangeProfile.route){
            ProfileChangeScreen(navController)
        }
    }
}