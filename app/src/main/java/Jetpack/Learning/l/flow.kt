package Jetpack.Learning.l

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow








class MainActivity: ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

                val vm: ViewObjs = viewModel()
                increment(vm.count, { vm.counter() })



        }
    }
        }







class ViewObjs: ViewModel(){
    private val _count= MutableStateFlow(0)
    val count=_count.asStateFlow()

    fun counter(){
        _count.value++
    }

}

@Composable
fun increment(flow: StateFlow<Int>, onClick:()->Unit) {
   val count by flow.collectAsState()
    Scaffold() {
        Column(modifier= Modifier.fillMaxSize()
            .padding(it)) {
            Text("$count",
                fontSize = 28.sp)
            Button(onClick={onClick()}) {
                Text("Increment")

            }
        }
    }

}