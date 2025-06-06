package org.schabi.newpipe.feature.feed

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.foundation.layout.Box
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import org.schabi.newpipe.core.model.Stream
import org.schabi.newpipe.core.ui.StreamItem
import org.schabi.newpipe.core.ui.ShimmeringStreamItem
import org.schabi.newpipe.core.ui.EmptyState
import org.schabi.newpipe.core.ui.components.MainNavigationBar
import org.schabi.newpipe.core.ui.components.MainTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(
    selectedTab: MainTab,
    onTabSelected: (MainTab) -> Unit,
    onStreamSelected: (Stream) -> Unit,
    viewModel: FeedViewModel = hiltViewModel()
) {
    val feed = viewModel.feed.collectAsState().value
    val refreshing = viewModel.isRefreshing.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val pullState = rememberPullRefreshState(refreshing, { viewModel.refresh() })

    Scaffold(
        topBar = { TopAppBar(title = { Text("Feed") }) },
        bottomBar = { MainNavigationBar(selectedTab, onTabSelected) }
    ) { padding ->
        Box(Modifier
            .fillMaxSize()
            .padding(padding)
            .pullRefresh(pullState)) {
            when {
                isLoading -> {
                    LazyColumn(Modifier.fillMaxSize()) {
                        items(8) { ShimmeringStreamItem() }
                    }
                }
                feed.isEmpty() -> {
                    EmptyState("Dein Feed ist leer.")
                }
                else -> {
                    LazyColumn(Modifier.fillMaxSize()) {
                        items(feed) { stream ->
                            StreamItem(stream, onStreamSelected)
                        }
                    }
                }
            }
            PullRefreshIndicator(
                refreshing,
                pullState,
                Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

