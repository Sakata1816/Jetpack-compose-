package Jetpack.Learning.l

import android.R
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme

import com.example.jetpackompose.ui.theme.JetpacKomposeTheme

import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import android.os.Bundle
import android.widget.CheckBox
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.TextField
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.graphicsLayer
import android.util.Log
import android.content.ContentValues.TAG
import android.text.Layout
import androidx.compose.material3.Scaffold
import android.R.attr.content
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.MutableState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Scaffold ()
              { innerPadding: PaddingValues->
Column {
    Box(
        contentAlignment= Alignment.Center,
        modifier =
            Modifier.padding(innerPadding)
                .fillMaxWidth()){
        MaincheckBox()
    }

    Box(
        contentAlignment= Alignment.Center,
        modifier =
            Modifier.padding(innerPadding)
                .fillMaxWidth()){
      expCheckox()
    }
}

                }


            }

        }
    }


@Composable
fun MaincheckBox(){
    var isChecked=remember { mutableStateOf(false) }
    Checkbox(
        checked = isChecked.value,
        onCheckedChange = {it->
            Log.i("!!!","mainCheckBox:$it")
            isChecked.value=it},
        modifier = Modifier.graphicsLayer(scaleX = 4f, scaleY = 4f),

    )

}

@Composable
fun expCheckox(){


    var checkedState by remember { mutableStateOf(true) }

    Checkbox(checked = checkedState,
        onCheckedChange = {it->
            checkedState=it},
        modifier = Modifier.graphicsLayer(scaleX = 3f, scaleY = 3f),)
}