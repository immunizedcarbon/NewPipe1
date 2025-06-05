package org.schabi.newpipe.feature.playlists

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.schabi.newpipe.core.model.LocalPlaylist
import org.schabi.newpipe.core.ui.components.MainNavigationBar
import org.schabi.newpipe.core.ui.components.MainTab

@Composable
fun PlaylistsScreen(
    onPlaylistSelected: (LocalPlaylist) -> Unit,
    selectedTab: MainTab = MainTab.Playlists,
    onTabSelected: (MainTab) -> Unit = {},
    viewModel: PlaylistsViewModel = hiltViewModel()
) {
    val playlists = viewModel.playlists.collectAsState().value
    var showDialog by remember { mutableStateOf(false) }
    var playlistName by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Playlists") }) },
        bottomBar = { MainNavigationBar(selectedTab, onTabSelected) },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(playlists) { playlist ->
                Text(
                    text = playlist.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable { onPlaylistSelected(playlist) }
                )
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.create(playlistName)
                    playlistName = ""
                    showDialog = false
                }) {
                    Text("Create")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) { Text("Cancel") }
            },
            title = { Text("New Playlist") },
            text = {
                OutlinedTextField(
                    value = playlistName,
                    onValueChange = { playlistName = it },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        )
    }
}

@Composable
fun PlaylistDetailScreen(id: Long) {
    Scaffold { padding ->
        Column(Modifier.fillMaxSize().padding(padding)) {
            Text("Playlist #$id")
        }
    }
}
