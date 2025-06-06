package org.schabi.newpipe.feature.downloads

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import org.schabi.newpipe.core.ui.components.MainNavigationBar
import org.schabi.newpipe.core.ui.components.MainTab

@Composable
fun DownloadsScreen(
    selectedTab: MainTab,
    onTabSelected: (MainTab) -> Unit,
    viewModel: DownloadsViewModel = hiltViewModel()
) {
    val downloads = viewModel.downloads.collectAsState().value
    Scaffold(
        topBar = { TopAppBar(title = { Text("Downloads") }) },
        bottomBar = { MainNavigationBar(selectedTab, onTabSelected) }
    ) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding)) {
            items(downloads) { item ->
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = item.title)
                    AsyncImage(model = item.thumbnailUrl, contentDescription = null)
                    LinearProgressIndicator(progress = item.progress / 100f, modifier = Modifier.fillMaxSize())
                    Text(text = item.status)
                }
            }
        }
    }
}
