package org.schabi.newpipe.feature.player

import androidx.compose.runtime.Composable

class NewPlayer : PlayerContract {
    @Composable
    override fun PlayerScreen(url: String) {
        DefaultPlayerScreen(url)
    }
}
