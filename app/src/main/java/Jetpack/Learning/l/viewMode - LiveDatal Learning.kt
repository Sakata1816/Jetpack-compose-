package Jetpack.Learning.l

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


//ViewModel в Android — это класс, который хранит и управляет данными UI и бизнес-логикой так, чтобы они переживали изменения конфигурации (например, поворот экрана, прокрутка, пересоздание Activity/Fragment).
// то есть viewModel это что то типа мини сервера который хранит все значения пока приложение не умрет



class MyViewModel:ViewModel(){
    var count by mutableStateOf(0)

    fun increase(){
        count++
    }
}

@Composable
fun CounterView(viewModel: MyViewModel=viewModel()){
    Column() {
        Text(text = "Count: ${viewModel.count}",
            fontSize = 24.sp)
        Button(onClick = { viewModel.increase() }) {
            Text(text = "Increase")
        }
    }
}

