package com.example.networkexperience.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.networkexperience.Screens.GetPost
import com.example.networkexperience.navRoutes.Navigator
import com.example.networkexperience.Screens.PostsAndComments


@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Navigator.PostsAndComments.route) {
        composable(Navigator.PostsAndComments.route) {
            PostsAndComments(navController)
        }
        composable(
            route = Navigator.Post.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            GetPost(id)
        }

        composable(Navigator.PostPatch.route) {

        }

    }
}