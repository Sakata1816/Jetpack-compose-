package DataBase.example.data.navigation

sealed class NavRoute(val route: String){
    object Main: NavRoute("Main")
    object FavouriteAnime: NavRoute("FavouriteAnime")
    object Anime: NavRoute("Main")
    object Settings: NavRoute("Settings")



}