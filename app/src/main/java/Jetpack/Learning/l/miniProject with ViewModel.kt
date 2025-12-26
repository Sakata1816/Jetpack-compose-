package Jetpack.Learning.l

import android.R.attr.onClick
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity4: ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background // Используем цвет фона из темы (обычно белый или темно-серый)
            ){
                Main()
            }



        }


    }
        }

data class Users(val name: String,val age: Int)
class UserListViewModel: ViewModel(){
    val user= mutableStateListOf<Users>(Users("Shax",19),Users("Said",19  ),Users("Shax",19),Users("Jahongir",19  ))
    var userName by mutableStateOf("")
    var userAge by mutableStateOf(0)

    fun addUser(){
        user.add(Users(userName,userAge))
    }
    fun removeUser(name: Users){
        user.remove(name)
    }
    fun changeName(name:String){
        userName=name
    }

    fun changeAge(age:String){
userAge=age.toIntOrNull()?:userAge
    }
}

@Composable
fun Main(vm:UserListViewModel= viewModel()){
    Column(){
        userData({vm.addUser()},vm.userName,vm.userAge,{vm.changeName(it)},{vm.changeAge(it)})
        userList(vm.user,{vm.removeUser(it)})
    }
}

@Composable
fun userData(addUser:()->Unit,userName: String,userAge: Int,changeName: (String)->Unit,changeAge: (String)->Unit){
    Column(modifier = Modifier.fillMaxWidth()
        .padding(WindowInsets.systemBars.asPaddingValues()),
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        TextField(
            value = userName,
            onValueChange = {changeName(it)},
            modifier = Modifier.padding(8.dp),
            label = {Text("Name")}
        )
        TextField(
            value = userAge.toString(),
            onValueChange = { changeAge(it) },
            modifier = Modifier.padding(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = {Text("Age")}
        )
        Button(onClick = {addUser()},
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "кнопка меню")
        }
    }


}

@Composable
fun userList(users:List<Users>,removeUser: (Users)-> Unit){
    LazyColumn() {
        items(users) {u->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${u.name}:",
                    modifier = Modifier.padding(12.dp),
                    fontSize = 25.sp
                )

                Text(
                    text = u.age.toString(),
                    modifier = Modifier.padding(12.dp),
                    fontSize = 25.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                TextButton(onClick = { removeUser(u) }) {
                    Icon(Icons.Filled.Delete, contentDescription = "кнопка удаления")
                }
            }


        }
    }

}



