package Jetpack.Learning.l

import androidx.compose.foundation.layout.Row

import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.clickable
import android.os.Bundle
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackompose.ui.theme.JetpacKomposeTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size


class mainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val langs=listOf(Language("Kotlin",0xFF81078a),
                Language("Java",0x40046b1b),
                Language("C++",0xFF0a5bf2))
            val count = remember{mutableStateOf(0)}

            val myColor = Color(0x40C95E00) //цвет
            val myPadding= PaddingValues(13.dp) //отступ
            Text(
                "Hello METANIT.COM\n" +
                        "njnjnj\n" +
                        "если двойкцу получил", //отоброжаемый текст
                fontSize = 28.sp, //размер
            color= Color(0xFFe814ff),
                modifier = Modifier
                    .padding(WindowInsets.statusBars.asPaddingValues())
                    .verticalScroll(rememberScrollState())
                    .horizontalScroll(rememberScrollState())

                    .background(myColor ) //задний фон
                    .padding(90.dp,10.dp) //для отступа
                    .clip(shape = RoundedCornerShape(40.dp))

                    .border(10.dp, shape = RoundedCornerShape(20.dp), color = Color.Blue,)  //для создания границ

                    .clip(shape = RoundedCornerShape(400.dp))  //для придавания формы границам элемента
                    .padding(15.dp)
                    .background(myColor)
                    .padding(myPadding)
                    .shadow(90.dp)


            )


            Text(
                text = "Clicks: ${count.value}",
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(100.dp,300.dp)
                    .clickable { count.value += 1 }
            )
            Text(
                 text = "Clean",
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(100.dp,350.dp)
                    .clickable { count.value = 0 }
            )

            Box(modifier = Modifier
                .padding(200.dp,450.dp)
                .size(200.dp,300.dp)

                .background(color = Color(0xff000000))){
                Text(
                    "Hello from box",
                    fontSize = 30.sp,
                    color = Color(0xFF30D51B),
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(shape = RoundedCornerShape(20.dp)

                    )

                )

            }

            Column(modifier= Modifier.fillMaxSize()
                .padding(top = 20.dp)
                .padding(horizontal = 10.dp)) {
                for(lang in langs){
                    Row(modifier = Modifier.padding(10.dp).fillMaxWidth()){
                        Box(modifier=Modifier.size(50.dp).background(Color(lang.color)))
                        Text(text = lang.name, fontSize = 28.sp, modifier = Modifier.padding(10.dp))
                    }
            }


            /*JetpacKomposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )

                    GreetingPreview()

                }
            }*/


        }
    }
}
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpacKomposeTheme {
        Greeting("Android")
    }
}

@Composable
fun text(value: String,modifier: Modifier= Modifier){
//Text(text(value), fontSize = 13.sp,modifier=modifier)
}





data class Language(val name: String,val color: Long)