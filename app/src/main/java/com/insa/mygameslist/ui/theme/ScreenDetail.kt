package com.insa.mygameslist


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.insa.mygameslist.data.IGDB
import com.insa.mygameslist.ui.theme.Be
import com.insa.mygameslist.ui.theme.MyV
import com.insa.mygameslist.ui.theme.MyV2
import com.insa.mygameslist.ui.theme.Pink80
import com.insa.mygameslist.ui.theme.Vf
import com.insa.mygameslist.ui.theme.afficherGenre2
import com.insa.mygameslist.ui.theme.afficherPlatforms
import com.insa.mygameslist.ui.theme.urlCover
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.insa.mygameslist.ui.theme.createAnimatedImageDrawableFromImageDecoder
import com.google.accompanist.drawablepainter.rememberDrawablePainter


@RequiresApi(Build.VERSION_CODES.P)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ScreenDetail(id: Int, favoris:  MutableList<Int>, onToggleFavorite: (Int) -> Unit, onBack: () -> Unit) {
    val game = IGDB.games.firstOrNull { it.id.toInt() == id }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MyV,
                    titleContentColor = Vf,
                ),
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()) {

                        Text(
                            text = game?.name ?: "Unknown",

                            modifier = Modifier.weight(1f)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        val isFavorite = favoris.contains(id)

                        Text(
                            text = if (isFavorite) "★" else "☆",
                            fontSize = 26.sp,
                            modifier = Modifier
                                .clickable { onToggleFavorite(id) }
                                .padding(end=8.dp)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_back),
                            contentDescription = "Retour"
                        )
                    }
                },
            )
        },
        contentWindowInsets = WindowInsets.systemBars,
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Image(
            painter = painterResource(R.drawable.bcd),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .padding(20.dp)
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(color = MyV2, shape = RoundedCornerShape(16.dp))
                .padding(all=15.dp)
            ) {

                Column(
                    modifier = Modifier.fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        game?.name ?: "Unknown",
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        color = Vf
                    )
                    AsyncImage(
                        modifier = Modifier.padding(15.dp)
                            .size(200.dp),
                        model = game?.let { urlCover(it.cover) },
                        contentDescription = "Cover du jeu"
                    )
                    Text(
                        text = afficherGenre2(G = game?.genres ?: emptyList()),
                        color = Vf,
                        fontStyle = FontStyle.Italic,
                        fontSize = 10.sp,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    afficherPlatforms(game?.platforms ?: emptyList())
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        game?.summary ?: "Unknown",
                        color = Vf
                    )

                }
            }
            Image(
                painter = rememberDrawablePainter(
                    drawable = createAnimatedImageDrawableFromImageDecoder(LocalContext.current, R.drawable.giffleurs)),
                contentDescription = "animated gif",
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.TopCenter)
                    .fillMaxSize()
            )

        }

    }
}


