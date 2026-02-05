package com.example.networkexperience.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.networkexperience.navRoutes.Navigator
import com.example.networkexperience.Screens.PostsAndComments


@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Navigator.PostsAndComments.route) {
        composable(Navigator.PostsAndComments.route) {
            PostsAndComments()
        }

        composable(Navigator.PostPatch.route) {

        }

    }
}