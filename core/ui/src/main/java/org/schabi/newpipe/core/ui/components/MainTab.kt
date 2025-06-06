package org.schabi.newpipe.core.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Subscriptions
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlaylistPlay
import androidx.compose.material.icons.filled.RssFeed
import androidx.compose.ui.graphics.vector.ImageVector

enum class MainTab(val route: String, val label: String, val icon: ImageVector) {
    Feed("feed", "Feed", Icons.Filled.RssFeed),
    Trends("main", "Trends", Icons.Filled.Home),
    History("history", "History", Icons.Filled.History),
    Subscriptions("subscriptions", "Subscriptions", Icons.Filled.Subscriptions),
    Playlists("playlists", "Playlists", Icons.Filled.PlaylistPlay)
}
