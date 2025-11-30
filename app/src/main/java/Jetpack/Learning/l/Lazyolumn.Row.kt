package Jetpack.Learning.l

import coil.compose.AsyncImage


import android.R
import androidx.compose.foundation.layout.Row
/*import com.example.gintama.R*/




import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.setValue
import com.example.jetpackompose.ui.theme.Pink80

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.semantics.semantics
import com.example.jetpackompose.ui.theme.Pink40

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme


/*fun LazyColumn(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical = if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
    content: LazyListScope.() -> Unit
): Unit*/

/*LazyListScope.item(): для добавления одного элемента

LazyListScope.items(): для добавления нескольких элементов

LazyListScope.itemsIndexed(): для добавления нескольких элементов с использованием индексов*/

//column= айтем с вертикальной прокрутокой , Создаёт все элементы сразу, даже те, что не видны.
//Lazy column - это то же самое что и column , айтем с верикальной прокруткой, Создаёт только видимые элементы, а при скролле подгружает новые и удаляет старые.



//внутри LazyListScope класса можно писать только обьекты item/items/Indexed


data class Languages(val name: String,val hex:Long)

val langs=listOf(Languages("Kotlin",0xff38b559),
    Languages("Java", 0xff2980b9),
    Languages("JavaScript", 0xffd35400),
    Languages("Python", 0xff2c3e50),
    Languages("Java", 0xff2980b9),
    Languages("JavaScript", 0xffd35400),
    Languages("Python", 0xff2c3e50),
    Languages("Java", 0xff2980b9),
    Languages("JavaScript", 0xffd35400),
    Languages("Python", 0xff2c3e50))

@Composable
fun LColumn(){



    LazyColumn(Modifier.size(300.dp,400.dp)
        .padding(15.dp)
        .background(Color.Green)){
        item{
            Text("Hello",
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.background(Color.Black))
        }

        items(20){
            Text("Элемент $it", fontSize = 20.sp, modifier = Modifier.padding(8.dp))
        }

        items(langs){
            Box( modifier = Modifier
                .fillMaxSize()
               ){
                Row(verticalAlignment = Alignment.CenterVertically,
                            modifier =  Modifier.height(80.dp)
                                .padding(10.dp)){
                            Box(Modifier.size(50.dp)
                                .background(color = Color(it.hex)))
                            Text(it.name,
                                Modifier.padding(horizontal = 25.dp),
                                color = MaterialTheme.colorScheme.onBackground)


                    }

                }
            }
        itemsIndexed(langs){it,lang->

            Text(
                "itmesIndexed \n" +
                        "index=$it\n" +
                        "${lang.name}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground,
                    modifier = if(it%2==1) {
                        Modifier.background(Color.Gray)
                    }else{
                        Modifier.background(Color.Red)
                    }
            )

        }

                }
            }




data class CalAndName(val color: Long,val name: String)
@Composable
fun LRow(){
    val box=Box(modifier = Modifier.background(Color(0xFF7D5260))){
        Text("qwe",
                modifier= Modifier.background(color = MaterialTheme.colorScheme.onBackground)
        )
    }

    val exp=listOf(CalAndName(0xFF4287f5,"gintama"),
        CalAndName(0xffd12429,"Kaguya sama love is war"),
        CalAndName(0xff0c107a,"Reside in dark"),
        CalAndName(0xff898aad,"kono suba"),
        CalAndName(0xff4ef591,"rezero"),
        CalAndName(0xffb3b012,"jojo"))


    LazyRow (modifier = Modifier.size(400.dp)
        .background(Color.Gray),
        horizontalArrangement = Arrangement.spacedBy(22.dp)
        ) {
        item {
            Text(
                "LazyRow",
                fontSize = 34.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .width(150.dp)
                    .background(Color.White)

            )
        }
        itemsIndexed(langs) { it, lang ->

                Text(
                    "${lang.name}",
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                     modifier = if(it%2==1) Modifier.background(color = Color(0xFF7D5260))
                    else{
                         Modifier.background(color = Color(0xFF202959))
                    }

                )


        }

        exp.forEach { it->
            item{

                Column(){
                    Box(modifier = Modifier.size(100.dp)
                        .background(Color(it.color))
                        .padding(10.dp)){
                        /*Image(
                            painter = painterResource(id = R.drawable._),
                            contentDescription = "Картинка WebP",
                            modifier = Modifier.size(150.dp),
                            contentScale = ContentScale.Crop
                        )*/
                    }
                    Text(it.name,
                        fontSize = 22.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.width(100.dp))

                }
            }

        }
    }
}







val anime=listOf(AnimeList("Gintama","https://www.google.com/imgres?q=gintama&imgurl=https%3A%2F%2Favatars.mds.yandex.net%2Fget-kinopoisk-image%2F4303601%2Ffe1077a6-8547-4f75-9849-8c409979f6db%2F600x900&imgrefurl=https%3A%2F%2Fwww.kinopoisk.ru%2Fseries%2F490128%2F&docid=Ce1spH7J2tHAUM&tbnid=hoQrTCuA6pBt2M&vet=12ahUKEwiWvIuzneeQAxXJORAIHb9-AHwQM3oECBsQAA..i&w=596&h=889&hcb=2&ved=2ahUKEwiWvIuzneeQAxXJORAIHb9-AHwQM3oECBsQAA"))

data class AnimeList(val name: String,val model: String)

