package Jetpack.Learning.l

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity3: ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isAdded = remember{ mutableStateOf(false) }
            Scaffold (modifier = Modifier.fillMaxSize(),
                topBar={
                    @OptIn(ExperimentalMaterial3Api::class)
                    TopAppBar(title={
                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            )
                        {
                            Text("My experience with learnin scaffold",
                                color = Color.Red,
                                modifier=Modifier.background(Color.Black)
                            )
                            Box(modifier = Modifier.weight(1f)
                                .height(30.dp)
                                .background(color = Color.DarkGray))
                        }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Gray,
                            titleContentColor = Color.Blue,
                            navigationIconContentColor = Color.Black,
                            actionIconContentColor = Color.Black
                        ),
                        navigationIcon = {
                            IconButton({}) {
                                Icon(Icons.Filled.Menu, contentDescription = "кнопка меню")
                            }
                        },
                        actions = {
                            IconButton({}) {
                                Icon(Icons.Filled.Info, contentDescription = "кнопка инфы")
                            }
                            IconButton({}) {
                                Icon(Icons.Filled.Search, contentDescription = "кнопка поиска")
                            }
                        }

                    )
                },
                bottomBar = {
                    BottomAppBar(containerColor = Color.Gray,
                        contentColor = Color.White) {
                        IconButton({}) {
                            Icon(Icons.Filled.Favorite, contentDescription = "кнопка поиска")
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton({}) {
                            Icon(Icons.Filled.Create, contentDescription = "кнопка поиска")
                        }

                    }
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = {isAdded.value=!isAdded.value}) {
                        if(isAdded.value) Icon(Icons.Filled.Clear, contentDescription = "Удалить")
                        else Icon(Icons.Filled.Add, contentDescription = "Добавить")
                    }
                },
                floatingActionButtonPosition = androidx.compose.material3.FabPosition.Center

                ){
                Text(text = if(isAdded.value)"Добавили" else "Не добавили",
                    fontSize = 24.sp,
                  modifier=  Modifier.padding(it))

            }



        }
    }
}
val PV= PaddingValues()
