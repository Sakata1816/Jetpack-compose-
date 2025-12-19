package Jetpack.Learning.l

import android.content.ContentValues
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun buttonCheck(){
    var buttonValue = remember { mutableStateOf(0) }
    var buttonValue1 = remember { mutableStateOf(0) }
    val button2=remember { mutableStateOf(0) }
val buttonText=if (button2.value==0)"click" else "back"

    val changeValue={ state: MutableState<Int>, newValue: Int ->
        state.value = newValue
    }

    LazyRow(modifier = Modifier.background(color=Color.Gray),
        contentPadding = PaddingValues(20.dp),
      horizontalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        item(0) {
            Column(modifier = Modifier.background(color=Color.Red)) {
                Button({changeValue(buttonValue,buttonValue.value+1)}) {
                    Text("${buttonValue.value}")
                }
                Button({changeValue(buttonValue,buttonValue.value+2)}) {
                    Text("${buttonValue.value}")
                }
                //значения полей этих кнопок зависят друг от друга потому что они обе показывают значение "buttonValue"
            }

        }
        item (1){
            Button({changeValue(buttonValue1,buttonValue1.value+2)}) {
                Text("${buttonValue1.value}")
            }
        }

        item(2) {
            Button(
                onClick = {button2.value=(button2.value+1)%2},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff004D40),
                    contentColor = Color.White,
                    disabledContainerColor = Color.Green,
                    // цвет при нажатии
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,  // обычная тень
                    pressedElevation = 2.dp,  // при нажатии тень уменьшается
                    disabledElevation = 0.dp  // нет тени если кнопка неактивна
                ),
                border = BorderStroke(3.dp, Color.DarkGray),
                shape = RoundedCornerShape(13.dp)
            ) {
                Text("${buttonText}")
            }
        }


    }
}