package org.schabi.newpipe.feature.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.schabi.newpipe.core.domain.usecase.GetStreamDetailsUseCase
import org.schabi.newpipe.core.domain.usecase.AddStreamToHistoryUseCase

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val player: ExoPlayer,
    private val getStreamDetails: GetStreamDetailsUseCase,
    private val addStreamToHistory: AddStreamToHistoryUseCase
) : ViewModel() {

    fun prepare(url: String) {
        viewModelScope.launch {
            val result = getStreamDetails(url).first()
            result.onSuccess {
                player.setMediaItem(MediaItem.fromUri(it.url))
                addStreamToHistory(it)
                player.prepare()
            }
        }
    }

    fun play() {
        player.play()
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }

    fun getPlayer(): ExoPlayer = player
}
