package org.schabi.newpipe.feature.playlists

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.schabi.newpipe.core.ui.components.MainNavigationBar
import org.schabi.newpipe.core.ui.components.MainTab
import org.schabi.newpipe.core.model.LocalPlaylist

@Composable
fun PlaylistsScreen(
    selectedTab: MainTab,
    onTabSelected: (MainTab) -> Unit,
    onSearchClicked: () -> Unit,
    onPlaylistSelected: (LocalPlaylist) -> Unit = {},
    viewModel: PlaylistsViewModel = hiltViewModel()
) {
    val playlists = viewModel.playlists.collectAsState().value
    val showDialog = remember { mutableStateOf(false) }
    val newName = remember { mutableStateOf("") }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.create(newName.value)
                    showDialog.value = false
                }) { Text("Create") }
            },
            dismissButton = {
                TextButton(onClick = { showDialog.value = false }) { Text("Cancel") }
            },
            text = {
                Column {
                    Text(text = "New Playlist")
                    OutlinedTextField(value = newName.value, onValueChange = { newName.value = it })
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Playlists") },
                actions = {
                    IconButton(onClick = onSearchClicked) {
                        Icon(Icons.Filled.Search, contentDescription = "Search")
                    }
                }
            )
        },
        bottomBar = { MainNavigationBar(selectedTab, onTabSelected) },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog.value = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding)) {
            items(playlists) { playlist ->
                Text(
                    text = playlist.name,
                    modifier = Modifier
                        .clickable { onPlaylistSelected(playlist) }
                        .padding(16.dp)
                )
            }
        }
    }
}
