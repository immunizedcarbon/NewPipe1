package org.schabi.newpipe.feature.player

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.weight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
        onDispose { player.release() }
    }
    val playlists = viewModel.playlists.collectAsState().value
    val showDialog = remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier.weight(1f),
            factory = { context ->
                PlayerView(context).apply { this.player = player }
            }
        )
        Button(onClick = { viewModel.subscribeToCurrentChannel() }) {
            Text("Subscribe")
        }
        Button(onClick = { showDialog.value = true }) {
            Text("Add to Playlist")
        }
    }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            confirmButton = {},
            title = { Text("Select Playlist") },
            text = {
                LazyColumn {
                    items(playlists) { playlist ->
                        Text(
                            text = playlist.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.addCurrentStreamToPlaylist(playlist.id)
                                    showDialog.value = false
                                }
                                .padding(8.dp)
                        )
                    }
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog.value = false }) { Text("Cancel") }
            }
        )
    }
}
