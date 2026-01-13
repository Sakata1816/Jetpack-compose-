package Jetpack.Learning.l

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


/*class MainActivity: ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val vm: Samples=viewModel()
            FlowScreen(vm.stateFlow,vm::incrase)

        }


    }
        }*/


class Samples: ViewModel(){
    private val _stateFlow= MutableStateFlow(0)
    val stateFlow=_stateFlow.asStateFlow()

    fun incrase(){
        _stateFlow.value++
    }

}

@Composable
fun FlowScreen(flow: StateFlow<Int>, onIncrease:()->Unit,modifier:Modifier){
    val count by flow.collectAsState()
    var messages = remember { mutableStateListOf<Int>() }

    LaunchedEffect(Unit) {
        flow.collect {
            messages.add(it)
        }
        }

    Column(modifier =modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center) {
        Text("$count",
            fontSize = 44.sp,
            )
        Button({onIncrease()}) {
            Text("Increment")

        }
        LazyRow() {
            items(messages.size) {
                Text(messages[it].toString(),
                    fontSize = 28.sp,
                    modifier=Modifier.padding(12.dp),
                    color = Color.Red)
            }
        }


    }

    }

