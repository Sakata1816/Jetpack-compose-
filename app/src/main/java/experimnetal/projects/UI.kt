package experimnetal.projects

import Jetpack.Learning.l.DrawerContent
import Jetpack.Learning.l.Screens
import Jetpack.Learning.l.main
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch


class MainActivity: ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold {
                main2(modifier = Modifier.padding(it)
                    .fillMaxSize())
            }

    }
        }
    }


@Composable
fun main2(UserVM: UserViewModel = viewModel(),modifier:Modifier){
    val uiStateM by UserVM.uiState.collectAsState()

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background)
    {
        UsersList( uiStateM.users)
    }

}


@Composable
fun UsersList(us: List<User>){
    LazyColumn {
        items(us){
            Row (modifier = Modifier
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically)
            {
                Text("id = ${it.userId.toString()}",
                    fontSize = 24.sp)
                Text("userId = ${it.id.toString()}",
                    fontSize = 24.sp)
                Text("title = ${it.title}",
                    fontSize = 24.sp)
                Text("body = ${it.body}",
                    fontSize = 24.sp)

            }


        }
    }
}