package Jetpack.Learning.l

import android.content.ContentValues
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Counter(){
    val clickState=remember { mutableStateOf(0) }
    val onClickChange={value:Int->
        clickState.value=value
    }
    Column() {
        Text("${clickState.value}",
            fontSize = 30.sp,
            modifier = Modifier.padding(10.dp))
        Increment(clickState.value,onClickChange)

    }

}

@Composable
fun Increment(value:Int,onClickChange:(Int)->Unit){
    Text("+",
        fontSize = 30.sp,
        modifier = Modifier.clickable(onClick = {onClickChange(value+1)}))

}
val localParams= compositionLocalOf{}
var username by remember { mutableStateOf("") }



@Composable
fun TextFieladCheck(){
   TextField(value=username,
       onValueChange = {username=it},
       modifier = Modifier.size(width = 150.dp, height = 50.dp),
       placeholder = {Text("Поле для ввода") },//Это текст-подсказка, который отображается, пока пользователь ничего не ввёл.
       leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) }, //Иконка слева от текста.
       trailingIcon = { Icon(Icons.Default.Clear, contentDescription = null) }, //Иконка справа от текста.
       prefix = { Text("Text:") }, //Компонент перед вводимым текстом, внутри поля.
       suffix = { Text("| here is the end of text") }, //Компонент после вводимого текста, внутри поля.
       supportingText = { Text("Пароль должен быть минимум 8 символов") } , //Текст под полем, который может давать подсказку или сообщение об ошибке.
       visualTransformation = PasswordVisualTransformation(),


   )

}