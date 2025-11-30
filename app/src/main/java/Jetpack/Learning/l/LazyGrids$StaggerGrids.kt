package Jetpack.Learning.l

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun grids(){
    val langs = listOf(Language("Kotlin", 0xff16a085),
        Language("Java", 0xff2980b9),
        Language("JavaScript", 0xff8e44ad),
        Language("Python", 0xff2c3e50),
        Language("Rust",0xffd35400),
        Language("C#",0xff27ae60),
        Language("C++",0xfff39c12),
        Language("Go",0xff1abc9c))
    Text(
        "lazyHorizontalGrid",
        fontSize = 20.sp

    )
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = Modifier.size(400.dp)
            .background(color = Color.LightGray),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.Top
    ){

        items(langs) {lang ->
            Column(Modifier.padding(8.dp).size(125.dp), horizontalAlignment = Alignment.CenterHorizontally){
                Box(Modifier.size(100.dp).background(Color(lang.color)))
                Text(lang.name, fontSize = 24.sp,modifier= Modifier.padding(5.dp))
            }
        }
    }
}

@Composable
fun LazyStaggeedGrids(){
   /* val boxList=listOf(box(0xFF01245c),
        box(0xFF351a70),
        box(0xFFc71ab8),
        box(0xFF4fc40c),
        box(0xFF4f000b))*/

    Text("LazyVertivalStaggeredGrid",
        fontSize = 30.sp,
        color = Color.Black)
    LazyVerticalStaggeredGrid(modifier = Modifier
        .fillMaxWidth()
        .height(600.dp)
        .background(Color.Red)
        .border(5.dp, shape = RoundedCornerShape(12.dp), color = Color.Black),

        columns = StaggeredGridCells.Fixed(4),
        horizontalArrangement = Arrangement.Start
        ) {
        items(50){
            Box(modifier = Modifier.height(Random.nextInt(50,400).dp)
                .background(Color( Random.nextInt(255),
                    Random.nextInt(255),
                    Random.nextInt(255),
                    255)))

        }
        /*item{


        }
        itemsIndexed(langs){it,lang->

            if(it%2==1){

            }
        }*/
    }

}


fun sum()={a:Int,b:Int->a+b}

@Composable
fun box(colorHex: Long)=Box(modifier = Modifier.height(Random.nextInt(50,200).dp)
    .background(Color(colorHex)))

