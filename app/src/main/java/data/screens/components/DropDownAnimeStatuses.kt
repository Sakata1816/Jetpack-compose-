package data.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


enum class AnimeStatus(val title: String, val color: Color) {
    NONE("Добавить в", Color.Gray),
    PLAN("В планах", Color.LightGray),
    WATCHING("Смотрю", Color.Blue),
    DROPPED("Брошено", Color.Red),
    COMPLETED("Просмотрено", Color.Green),
    DELETED("Удалить из списка", Color.Red)
}

@Composable
fun StatusDropdown(
    currentStatus: AnimeStatus,
    onStatusSelected: (AnimeStatus) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {

        Button(
            onClick = { expanded = true },
            colors = ButtonDefaults.buttonColors(
                containerColor = currentStatus.color
            )
        ) {
            Text(
                currentStatus.title
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            AnimeStatus.values().drop(1).forEach { status ->

                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .background(status.color, CircleShape)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(status.title)
                        }
                    },
                    onClick = {
                        expanded = false
                        onStatusSelected(status)
                    }
                )
                Divider()
            }


        }
    }
}