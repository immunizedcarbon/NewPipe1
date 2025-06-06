package org.schabi.newpipe.feature.playlist_detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import org.schabi.newpipe.core.model.Stream
import org.schabi.newpipe.core.ui.StreamItem
import org.schabi.newpipe.core.ui.ShimmeringStreamItem
import org.schabi.newpipe.core.ui.EmptyState

@Composable
fun PlaylistDetailScreen(
    onStreamSelected: (Stream) -> Unit,
    viewModel: PlaylistDetailViewModel = hiltViewModel()
) {
    val streams = viewModel.streams.collectAsState().value
    val name = viewModel.playlistName.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value

    Scaffold(
        topBar = { TopAppBar(title = { Text(name) }) }
    ) { padding ->
        when {
            isLoading -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) { items(8) { ShimmeringStreamItem() } }
            }
            streams.isEmpty() -> {
                EmptyState("Playlist ist leer.")
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    items(streams) { stream ->
                        StreamItem(stream, onStreamSelected)
                    }
                }
            }
        }
    }
}

