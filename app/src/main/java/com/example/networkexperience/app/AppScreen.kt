package com.example.networkexperience.app

import Jetpack.Learning.l.About
import Jetpack.Learning.l.Contacts
import Jetpack.Learning.l.DrawerContent
import Jetpack.Learning.l.FlowScreen
import Jetpack.Learning.l.Home
import Jetpack.Learning.l.Main
import Jetpack.Learning.l.Samples
import Jetpack.Learning.l.Screens
import Jetpack.Learning.l.Titles
import Jetpack.Learning.l.ViewObjs
import Jetpack.Learning.l.increment
import Jetpack.Learning.l.main1
import Jetpack.Learning.l.scaffold
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.networkexperience.Screens.ScreensColumn
import kotlinx.coroutines.launch

@Composable
fun Menu(modifier: Modifier= Modifier) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val drawerWidth = screenWidth * 0.3f // 30% ширины

    Scaffold(Modifier.fillMaxSize()) {it->

        ModalNavigationDrawer(
            modifier = Modifier.padding(it),
            drawerState = drawerState,
            drawerContent = {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(drawerWidth)
                        .background(Color.LightGray)
                        .padding(8.dp)
                ) {
                    Column() {
                        ScreensColumn(onItemClick = { route ->
                            scope.launch {
                                drawerState.close()
                            }
                            navController.navigate(route) {
                                launchSingleTop = true
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                restoreState = true
                            }
                        }
                        )
                    }

                }
            },
            scrimColor = Color.DarkGray
        ) {
            Column() {
                IconButton(onClick = {
                    scope.launch { drawerState.open() }
                }) {
                    Icon(Icons.Filled.Menu, "Меню")
                }
                AppNavGraph(navController)


            }

        }
    }


}