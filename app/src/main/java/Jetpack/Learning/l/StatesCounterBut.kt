package Jetpack.Learning.l

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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