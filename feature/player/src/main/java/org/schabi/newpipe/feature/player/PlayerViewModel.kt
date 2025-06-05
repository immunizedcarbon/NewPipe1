package org.schabi.newpipe.feature.player

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private var player: ExoPlayer? = null

    fun prepare(url: String) {
        if (player == null) {
            player = ExoPlayer.Builder(context).build()
        }
        player?.setMediaItem(MediaItem.fromUri(url))
        player?.prepare()
    }

    fun play() {
        player?.play()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            player?.release()
            player = null
        }
    }

    fun getPlayer(): ExoPlayer? = player
}
