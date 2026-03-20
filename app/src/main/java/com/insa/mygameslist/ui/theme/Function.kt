package com.insa.mygameslist.ui.theme

import android.content.Context
import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.insa.mygameslist.data.IGDB
import com.insa.mygameslist.data.IGDB.genres


fun afficherGenre(G: List<Long>): String {
    var afficher = "Genres : "
    G.forEachIndexed { index, idG ->
        afficher += genres.find { it.id == idG }?.name ?: "Inconnu"
        if (index != G.lastIndex) {
            afficher += ", "
        }
    }
    return afficher
}
fun afficherGenre2(G: List<Long>): String {
    var afficher = ""
    G.forEachIndexed { index, idG ->
        afficher += genres.find { it.id == idG }?.name ?: "Inconnu"
        if (index != G.lastIndex) {
            afficher += ", "
        }
    }
    return afficher
}
fun urlCover(idCover : Long):String{
    for (cov in IGDB.covers){
        if(cov.id==idCover){
            return "https:"+cov.url
        }
    }
    return ""
}
@Composable
fun afficherPlatforms(LPlat: List<Long>) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
    ) {
        for (plat in IGDB.platforms) {
            if (plat.id in LPlat) {
                val logo = IGDB.platformLogos.find { it.id == plat.platform_logo }
                logo?.let {
                    AsyncImage(
                        model = "https:${it.url}",
                        contentDescription = "Logo plateforme",
                        modifier = Modifier.size(80.dp)
                    )
                }
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.P)
fun createAnimatedImageDrawableFromImageDecoder(context: Context, resId: Int): AnimatedImageDrawable {
    val source = ImageDecoder.createSource(context.resources, resId)
    val drawable = ImageDecoder.decodeDrawable(source)
    return drawable as AnimatedImageDrawable
}


