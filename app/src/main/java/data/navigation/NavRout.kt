package data.navigation

sealed class NavRoute(val route: String){
    object Main: NavRoute("Main")
    object FavouriteAnime: NavRoute("FavouriteAnime")
    object Settings: NavRoute("Settings")

    object AnimeDetails : NavRoute("anime_details/{animeId}") {
        fun createRoute(animeId: Int) = "anime_details/$animeId"
    }

    object Episodes: NavRoute("anime_episodes/{animeId}"){
        fun createRoute(animeId: Int) = "anime_episodes/$animeId"
    }

}