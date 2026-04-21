package AnimeJ.presentation.navigation.mainRoot

sealed class NavRoute(val route: String){

    object AuthGraph : NavRoute("auth_graph")

    object Main: NavRoute("Main")
    object FavouriteAnime: NavRoute("FavouriteAnime")
    object AnimeDetails : NavRoute("anime_details/{animeId}") {
        fun createRoute(animeId: Int) = "anime_details/$animeId"
    }

    object Episodes: NavRoute("anime_episodes/{animeId}"){
        fun createRoute(animeId: Int) = "anime_episodes/$animeId"
    }
    object Settings: NavRoute("settings")
    object ChangeProfile: NavRoute("change_profile")



}