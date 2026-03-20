package com.insa.mygameslist.ui.theme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.insa.mygameslist.R
import com.insa.mygameslist.data.IGDB

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenGameListe(click: (Int) -> Unit, favoris: MutableList<Int>, onToggleFavorite: (Int) -> Unit) {
    val currentFavoris = favoris
    var query by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }


    val filteredGames = IGDB.games.filter { game ->
        val q = query.trim()
        if (q.isEmpty()) {
            true
        } else {
            val genreNames = game.genres.mapNotNull { id ->
                IGDB.genres.firstOrNull { it.id == id }?.name
            }
            val platformNames = game.platforms.mapNotNull { id ->
                IGDB.platforms.firstOrNull { it.id == id }?.name
            }
            game.name.contains(q, ignoreCase = true) ||
                    genreNames.any { it.contains(q, ignoreCase = true) } ||
                    platformNames.any { it.contains(q, ignoreCase = true) }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MyV,
                    titleContentColor = Vf,
                ),
                navigationIcon = {
                    if (!expanded) {
                        Text(
                            "🔍",
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .clickable { expanded = true }
                        )
                    }
                },
                title = {
                    if (!expanded) {
                        Text("MyGamesListe")
                    } else {
                        CustomizableSearchBar(
                            modifier = Modifier.fillMaxWidth(),
                            query = query,
                            onQueryChange = { query = it },
                            expanded = expanded,
                            onExpandedChange = { expanded = it },
                            searchResults = filteredGames.map { it.name },
                            onResultClick = { name ->
                                val game = IGDB.games.first { it.name == name }
                                click(game.id.toInt())
                            },
                            supportingContent = { gameName ->
                                val game = IGDB.games.first { it.name == gameName }
                                val genreNames = game.genres.mapNotNull { id ->
                                    IGDB.genres.firstOrNull { it.id == id }?.name
                                }
                                Text(genreNames.joinToString(", "))
                            }

                        )
                    }
                }
            )
        },
        contentWindowInsets = WindowInsets.systemBars,
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        Box(modifier = Modifier.fillMaxSize()) {

            Image(
                painter = painterResource(R.drawable.bc),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 8.dp)
            ) {
                if (filteredGames.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 50.dp),

                            contentAlignment = Alignment.Center
                        ) {
                            Text(text="No match :(",
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp,
                                color = Vf

                            )
                        }
                    }
                } else {
                    items(filteredGames.size) { index ->
                        game(
                            game = filteredGames[index],
                            isFavorite = favoris.contains(filteredGames[index].id.toInt()),
                            onClick = { click(filteredGames[index].id.toInt()) },
                            onFavoriteClick = {
                                onToggleFavorite(filteredGames[index].id.toInt())
                            }

                        )

                    }
                }
            }

            Image(
                painter = rememberDrawablePainter(
                    drawable = createAnimatedImageDrawableFromImageDecoder(LocalContext.current, R.drawable.gifchat)),
                contentDescription = "animated gif",
                modifier = Modifier
                    .size(125.dp)
                    .align(Alignment.BottomStart)
                    .padding(all=25.dp)
            )

        }
        Box(modifier = Modifier.fillMaxSize()){
            Image(
                painter = rememberDrawablePainter(
                    drawable = createAnimatedImageDrawableFromImageDecoder(LocalContext.current, R.drawable.gifchaat)),
                contentDescription = "animated gif",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .fillMaxHeight()
                    .padding(start = 300.dp)
                    .padding(bottom = 550.dp)
                    .padding(top = 150.dp)

            )
        }

    }
}
