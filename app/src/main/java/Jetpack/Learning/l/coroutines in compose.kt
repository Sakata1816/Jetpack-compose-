package Jetpack.Learning.l

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
GlobalScope: используется для запуска корутин верхнего уровня, которые привязаны ко всему жизненному циклу приложения. Поскольку корутины в этой области могут продолжать работать, когда в этом нет необходимости (например, когда объект Activity завершает свою работу), использование этой области не рекомендуется для использования в приложениях Android.

ViewModelScope: применяется при использовании компонента ViewModel архитектуры Jetpack. Корутины, запущенные в этой области из объекта ViewModel, автоматически завершаются системой при уничтожении соответствующего объекта ViewModel.

LifecycleScope: создается для каждого компонента с жизненным циклом и удаляется, когда уничтожается соответствующий компонент.*/



class MainActivity8: ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold() { it ->
                 val scope = rememberCoroutineScope()
                val enabled= remember{mutableStateOf(true)}
                val count=remember{mutableStateOf(0)}
                 Column(modifier = Modifier.padding(it),
                     verticalArrangement = Arrangement.Center) {
                    Text(text = "${count.value}",
                        fontSize = 34.sp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                        )
                     Button(onClick = {
                         scope.launch {
                             enabled.value = false
                             for(n in 1..5){
                                 count.value += n
                                 delay(1000)
                             }
                             enabled.value = true
                         }
                     }, enabled = enabled.value) {
                         Text("Start", fontSize = 28.sp)
                     }

                     Counter1()
                 }

             }
        }
    }
}


suspend fun dowork(){
    println("first ")

    delay(2000)
    println("second")
}


@Composable
fun Counter1() {
    val count = remember{mutableStateOf(0)}
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch() {
            for(n in 1..100){
                count.value = n
                delay(1000)
            }
        }
    }
    Text("Count: ${count.value}", Modifier.padding(start = 10.dp), fontSize = 28.sp)
}