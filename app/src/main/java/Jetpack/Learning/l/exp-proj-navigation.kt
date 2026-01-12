package Jetpack.Learning.l


import Jetpack.Learning.l.Main
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
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
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

class MainActivity: ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
          Menu()


        }
    }
}


@Composable
fun Navlist(naviagte: NavHostController){
    


}

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
                        DrawerContent(
                            onItemClick = { route ->
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
                        Screens(navController)
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
                NavHost(navController = navController, startDestination = Titles.Home.route) {
                    composable(Titles.Home.route) {
                        Home()
                    }
                    composable(Titles.Contacts.route) {
                        Contacts()
                    }
                    composable(Titles.About.route) {
                        About()
                    }
                    composable(Titles.Flow.route) {
                        val vm: ViewObjs = viewModel()
                        increment(vm.count,  vm::counter )
                    }

                    composable(Titles.FlowDif.route) {
                        val vm: Samples=viewModel()
                        Scaffold {
                            FlowScreen(vm.stateFlow,vm::incrase,Modifier.padding(it))
                        }
                    }
                    composable(Titles.Cars.route) {
                        main1()
                    }
                    composable(Titles.Main.route) {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background // Используем цвет фона из темы (обычно белый или темно-серый)
                        ) {
                            Main()
                        }
                    }
                }
            }

        }
    }


}


@Composable
fun DrawerContent(
    onItemClick: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        DrawerItem("Home", "Home", onItemClick)
        DrawerItem("Contacts", "Contacts", onItemClick)
        DrawerItem("Main", "Main", onItemClick)
        DrawerItem("About", "About", onItemClick)
        DrawerItem("Cars", "Cars", onItemClick)
        DrawerItem("Flow", "Flow", onItemClick)
        DrawerItem("Flow2", "FlowDif", onItemClick)
    }

}


@Composable
fun DrawerItem(
    title: String,
    route: String,
    onClick: (String) -> Unit
) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(route) }
            .padding(18.dp),
        fontSize = 24.sp
    )
}



@Composable
fun Screens(navController: NavHostController){
    Column(modifier = Modifier.fillMaxHeight()
        .padding(horizontal = 20.dp)) {
        Text("Main Screen",
            modifier = Modifier.clickable(onClick = {navController.navigate(Titles.Main.route)}),
            fontSize = 24.sp,
            color = Color.Red)
        Text("About Screen",
            modifier = Modifier.clickable(onClick = {navController.navigate(Titles.About.route)}),
            fontSize = 24.sp,
            color = Color.Red)
        Text("Contacts Screen",
            modifier = Modifier.clickable(onClick = {navController.navigate(Titles.Contacts.route) }),
            fontSize = 24.sp,
            color = Color.Red)
        Text("Home Screen",
            modifier = Modifier.clickable(onClick = {navController.navigate(Titles.Home.route)}),
            fontSize = 24.sp,
            color = Color.Red)
    }
}






sealed class Titles(val route: String){
    object Main: Titles("Main")
    object About: Titles("About")
    object Contacts: Titles("Contacts")
    object Home: Titles("Home")
    object  Cars: Titles("Cars")
    object Flow: Titles("Flow")
    object FlowDif: Titles("FlowDif")
}


