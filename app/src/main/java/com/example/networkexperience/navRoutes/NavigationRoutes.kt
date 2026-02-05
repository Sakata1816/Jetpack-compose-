package com.example.networkexperience.navRoutes

sealed class Navigator(val route: String) {
    object PostsAndComments : Navigator("PostsAndComments")
    object PostPatch : Navigator("PostPatch")
    object PostDelete : Navigator("PostDelete")
    object PostPut : Navigator("PostPut")

}


