package org.schabi.newpipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import org.schabi.newpipe.core.ui.theme.NewPipeTheme
import org.schabi.newpipe.feature.player.NewPlayer

@AndroidEntryPoint
class NewPlayerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url = intent.getStringExtra("url") ?: ""
        setContent {
            NewPipeTheme {
                NewPlayer().PlayerScreen(url)
            }
        }
    }
}
