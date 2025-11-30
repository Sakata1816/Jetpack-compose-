package Jetpack.Learning.l

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme

import com.example.jetpackompose.ui.theme.JetpacKomposeTheme

import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.material3.Surface
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.setValue
import com.example.jetpackompose.ui.theme.Pink80


/*class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            JetpacKomposeTheme{
                SimpleScreen()
            }


        }
    }
    }*/



@Composable
fun SimpleScreen() {

    Surface (modifier = Modifier,
        shape = RectangleShape,
        color = MaterialTheme.colorScheme.surface,
        Color.Blue,
        1.dp,
        2.dp

        ){

        Column(
            Modifier
                .padding(WindowInsets.statusBars.asPaddingValues())
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()), // чтобы можно было скроллить, если не помещается
            horizontalAlignment = Alignment.CenterHorizontally, // всё по центру по горизонтали
            verticalArrangement = Arrangement.Top // элементы сверху вниз
        ) {
            // Заголовок
            Text(
                text = "Пример экрана",
                fontSize = 28.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp),
                )

            // Изображение (пока просто цветной блок)
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.Cyan),
                contentAlignment = Alignment.Center
            ) {
                Text("Image", color = MaterialTheme.colorScheme.onBackground)
            }

            Spacer(modifier = Modifier.height(16.dp)) // отступ

            // Поле ввода
            var text by remember { mutableStateOf("") }
           TextField(
                value = text,

                onValueChange = { text = it },
                label = { Text("Введите текст") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Кнопка
            Button(
                onClick = { text="Вы нажали" },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Нажать")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Горизонтальный блок (Row)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Box(modifier = Modifier.size(50.dp).background(Color.Red))
                Box(modifier = Modifier.size(50.dp).background(Color.Green))
                Box(modifier = Modifier.size(50.dp).background(Color.Blue))
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Элемент, который внизу экрана
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text("Нижний блок", color = MaterialTheme.colorScheme.onBackground)
            }
            LColumn()
            LRow()
            grids()
            LazyStaggeedGrids()
        }
    }
    // Внешний контейнер, который занимает весь экран

}
