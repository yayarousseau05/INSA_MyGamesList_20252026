package com.insa.mygameslist

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.insa.mygameslist.data.IGDB
import com.insa.mygameslist.ui.theme.MyGamesListTheme
import com.insa.mygameslist.ui.theme.ScreenGameListe


data object Home
data class Detail(val id: Int)

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        IGDB.load(this)
        enableEdgeToEdge()
        setContent {
            MyGamesListTheme {
                val favoris = remember { mutableStateListOf<Int>() }

                val backStack = remember { mutableStateListOf<Any>(Home) }

                NavDisplay(
                    backStack = backStack,
                    onBack = { backStack.removeLastOrNull() },
                    entryProvider = { key ->
                        when (key) {
                            is Home -> NavEntry(key) {
                                ScreenGameListe(
                                    click = { id -> backStack.add(Detail(id)) },favoris=favoris,
                                    onToggleFavorite = { favId ->
                                        if (favoris.contains(favId)) favoris.remove(favId)
                                        else favoris.add(favId)
                                    }
                                )
                            }

                            is Detail -> NavEntry(key) {
                                ScreenDetail(
                                    id = key.id,
                                    favoris = favoris,
                                    onToggleFavorite = { favId ->
                                        if (favoris.contains(favId)) favoris.remove(favId)
                                        else favoris.add(favId)
                                    },
                                    onBack = { backStack.removeLastOrNull() }
                                )
                            }

                            else -> NavEntry(Unit) { Text("Unknown route") }
                        }
                    }
                )
            }
        }
    }
}
