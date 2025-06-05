package org.schabi.newpipe.feature.player

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun DefaultPlayerScreen(url: String, viewModel: PlayerViewModel = hiltViewModel()) {
    LaunchedEffect(url) {
        viewModel.prepare(url)
        viewModel.play()
    }
    val player = viewModel.getPlayer()
    DisposableEffect(Unit) {
        onDispose {
            player?.release()
        }
    }
    AndroidView(modifier = Modifier.fillMaxSize(), factory = { context ->
        PlayerView(context).apply {
            this.player = player
        }
    })
}
