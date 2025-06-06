package org.schabi.newpipe.feature.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import org.schabi.newpipe.core.model.Stream
import org.schabi.newpipe.core.ui.StreamItem
import org.schabi.newpipe.core.ui.components.MainNavigationBar
import org.schabi.newpipe.core.ui.components.MainTab

@Composable
fun MainScreen(
    onStreamSelected: (Stream) -> Unit,
    selectedTab: MainTab,
    onTabSelected: (MainTab) -> Unit,
    onSearchClicked: () -> Unit,
    onSettingsClicked: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState().value
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "NewPipe") },
                actions = {
                    var expanded by remember { mutableStateOf(false) }
                    IconButton(onClick = onSearchClicked) {
                        Icon(Icons.Filled.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "Menu")
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        DropdownMenuItem(text = { Text("Settings") }, onClick = { expanded = false; onSettingsClicked() })
                    }
                }
            )
        },
        bottomBar = { MainNavigationBar(selectedTab, onTabSelected) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(state.trendingStreams) { stream ->
                StreamItem(stream, onStreamSelected)
            }
        }
    }
}
