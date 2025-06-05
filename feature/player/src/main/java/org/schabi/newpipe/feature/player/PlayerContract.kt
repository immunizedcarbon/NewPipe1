package org.schabi.newpipe.feature.player

import androidx.compose.runtime.Composable

interface PlayerContract {
    @Composable
    fun PlayerScreen(url: String)
}
