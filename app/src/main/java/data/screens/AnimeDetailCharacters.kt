package data.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import data.domain.model.server.CharacterItemModel

@Composable
fun AnimeCharactersContetnt(characters: List<CharacterItemModel> ){
    LazyColumn() {
        items(characters){character->
            Row(){
                AsyncImage(
                    model = character.images?.jpg ?.image_url ?: character.images?.webp?.image_url,
                    contentDescription = "AnimeChatactersImage",
                    modifier = Modifier.size(400.dp,300.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(text = character.name)

            }
        }
    }

}