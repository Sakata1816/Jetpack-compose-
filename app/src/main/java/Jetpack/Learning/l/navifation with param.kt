package Jetpack.Learning.l

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

data class Cars(val name: String,val color: String)

class CarListViewModel: ViewModel(){
    val cars= mutableStateListOf<Cars>(Cars("Lamborghini","Red"),Cars("Ferrari","White"))
        var name=mutableStateOf("")
    var color=mutableStateOf("")

    fun addCar(){
        cars.add(Cars(name.value,color.value))
    }
    fun removeCar(name: Cars){
        cars.remove(name)
    }
    fun changeName(name:String){
        this.name.value=name
    }
    fun changeColor(color: String){
        this.color.value=color
    }


}

@Composable
fun CarList(cars:List<Cars>,removeCar: (Cars)-> Unit) {
    LazyColumn() {
        items(cars) { c ->
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${c.name}:",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(12.dp)

                )
                Box(Modifier.size(30.dp)
                    .background(colorRewrite(c.color))
                )
                Spacer(modifier = Modifier.weight(1f)
                )
                Button({removeCar(c)}) {
                    Icon(Icons.Filled.Delete, contentDescription = "кнопка удаления")
                }
            }
        }
    }
}

fun colorRewrite(color:String):Color{
    return when(color.trim().lowercase()){
        "red"->Color.Red
        "white"->Color.White
        "yellow"->Color.Yellow
        "blue"->Color.Blue
        "green"->Color.Green
        else->Color.Black
    }
}

@Composable
fun CarData(cars:List<Cars>, addCar:()->Unit, name:String, color: String, changeName:(String)->Unit, changeColor:(String)->Unit){
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(value = name,
            onValueChange = {changeName(it)},
            modifier = Modifier.padding(8.dp),
            label = {Text("Name")})
        TextField(value = color,
            onValueChange = {changeColor(it)},
            modifier = Modifier.padding(8.dp),
            label = {Text("Color")})
        Button({addCar()}) {
            Icon(Icons.Filled.Add, contentDescription = "кнопка удаления")
        }
    }


}

@Composable
fun main1(vm:CarListViewModel= viewModel()){
Column {
    CarData(vm.cars,{vm.addCar()},vm.name.value,vm.color.value,{vm.changeName(it)},{vm.changeColor(it)} )
CarList(vm.cars,{vm.removeCar(it)})
}

}

