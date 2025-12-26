package Jetpack.Learning.l

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Contacts
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


class MainActivity: ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
        Scaffold() {
            main(Modifier.padding(it))
        }
            }
        }
    }

    @Composable
    fun main(modifier: Modifier = Modifier) {
        val navController = rememberNavController()
    Column(Modifier.padding(8.dp)){
        navigation(navController = navController)
        NavHost(navController = navController, startDestination = NavRoute.Home.route){
            composable(NavRoute.Home.route){
                Home()
            }
            composable(NavRoute.Contacts.route){
                Contacts()
            }
            composable(NavRoute.About.route){
                About()
            }
            composable(NavRoute.Main.route){
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background // Используем цвет фона из темы (обычно белый или темно-серый)
                ){
                    Main()
                }
            }
        }
    }

}


@Composable
fun navigation(navController: NavHostController) {
  Row(modifier = Modifier.fillMaxWidth()
      .padding(WindowInsets.systemBars.asPaddingValues())
      .padding(horizontal = 20.dp)){
      Text("Home",
          Modifier.weight(0.25f)
              .clickable{navController.navigate(NavRoute.Home.route)},
          fontSize = 24.sp,
         color= Color.Red)
      Text("Contacts",
          Modifier.weight(0.25f)
              .clickable{navController.navigate(NavRoute.Contacts.route)},
          fontSize = 24.sp,
          color= Color.Green)
      Text("About",
          Modifier.weight(0.25f)
              .clickable{navController.navigate(NavRoute.About.route)},
          fontSize = 24.sp,
          color= Color.Blue)
      Text("Main",
          Modifier.weight(0.25f)
              .clickable{navController.navigate(NavRoute.Main.route)},
          fontSize = 24.sp,
          color= Color.Blue)


  }

}

@Composable
fun Home(){
    Text("Home Screen",
        fontSize = 24.sp,
       modifier =  Modifier.padding(20.dp)
    )
    }

@Composable
fun Contacts(){
    Text("Contacts Screen",
        fontSize = 24.sp,
        modifier =  Modifier.padding(20.dp))
}

@Composable
fun About(){
    Text("About Screen",
        fontSize = 24.sp,
        modifier =  Modifier.padding(20.dp))
}

sealed class NavRoute(val route:String) {
object Home: NavRoute("main")
object Contacts: NavRoute("contacts")
object About: NavRoute("about")
    object Main: NavRoute("Main")
}
