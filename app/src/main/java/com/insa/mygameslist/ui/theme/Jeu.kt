package com.insa.mygameslist.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import coil3.compose.AsyncImage
import com.insa.mygameslist.data.*


@Composable
fun game(game: Game, onClick : () -> Unit, isFavorite: Boolean, onFavoriteClick: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(color = Be, shape = RoundedCornerShape(16.dp))
            .clickable{onClick()}
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(end=25.dp)
        ) {
            AsyncImage(
                model = urlCover(game.cover),
                contentDescription = "Cover du jeu",
                modifier = Modifier.size(80.dp)
            )

            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Row () {
                    Text(
                        text = game.name,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline,
                        fontSize = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Vf
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = afficherGenre(game.genres),
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Vf
                )
            }

        }

        Text(
            text = if (isFavorite) "★" else "☆",
            fontSize = 25.sp,
            color = Vf,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clickable { onFavoriteClick() }
        )


    }
}
