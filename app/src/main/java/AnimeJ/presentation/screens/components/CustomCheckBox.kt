package AnimeJ.presentation.screens.components

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun RoundCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(
                if (checked) MaterialTheme.colorScheme.onSurface
                    else Color.Transparent
            )
            .border(
                width = 2.dp,
                color = if (checked) {
                    MaterialTheme.colorScheme.primary
                }else{
                    MaterialTheme.colorScheme.onSurface
                },
                shape = CircleShape
            )
            .clickable {
                onCheckedChange(!checked)
            }
    )
}