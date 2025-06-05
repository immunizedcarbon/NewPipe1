package org.schabi.newpipe.feature.history

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import org.schabi.newpipe.core.model.Stream
import org.schabi.newpipe.core.ui.StreamItem
import org.schabi.newpipe.core.ui.components.MainNavigationBar
import org.schabi.newpipe.core.ui.components.MainTab

@Composable
fun HistoryScreen(
    onStreamSelected: (Stream) -> Unit,
    selectedTab: MainTab,
    onTabSelected: (MainTab) -> Unit,
    onSearchClicked: () -> Unit,
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val history = viewModel.history.collectAsState().value
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("History") },
                actions = {
                    IconButton(onClick = onSearchClicked) {
                        Icon(Icons.Filled.Search, contentDescription = "Search")
                    }
                }
            )
        },
        bottomBar = { MainNavigationBar(selectedTab, onTabSelected) }
    ) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding)) {
            items(history) { stream ->
                StreamItem(stream, onStreamSelected)
            }
        }
    }
}
