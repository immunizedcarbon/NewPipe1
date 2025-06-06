package org.schabi.newpipe.feature.subscriptions

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.clickable
import org.schabi.newpipe.core.model.Channel
import org.schabi.newpipe.core.ui.components.MainNavigationBar
import org.schabi.newpipe.core.ui.components.MainTab

@Composable
fun SubscriptionsScreen(
    selectedTab: MainTab,
    onTabSelected: (MainTab) -> Unit,
    onSearchClicked: () -> Unit,
    onChannelSelected: (Channel) -> Unit,
    viewModel: SubscriptionsViewModel = hiltViewModel()
) {
    val subs = viewModel.subscriptions.collectAsState().value
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Subscriptions") },
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
            items(subs) { channel ->
                ChannelRow(channel) { onChannelSelected(channel) }
            }
        }
    }
}

@Composable
private fun ChannelRow(channel: Channel, onClick: () -> Unit) {
    Row(modifier = Modifier.padding(8.dp).clickable(onClick = onClick)) {
        AsyncImage(model = channel.avatarUrl, contentDescription = null)
        Spacer(Modifier.width(8.dp))
        Text(text = channel.name)
    }
}
