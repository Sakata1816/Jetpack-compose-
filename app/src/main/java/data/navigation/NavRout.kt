package data.navigation

sealed class NavRoute(val route: String){
    object Main: NavRoute("Main")
    object FavouriteAnime: NavRoute("FavouriteAnime")
    object Settings: NavRoute("Settings")



}