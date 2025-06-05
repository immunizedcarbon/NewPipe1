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
import org.schabi.newpipe.core.domain.usecase.SubscribeToChannelUseCase
import org.schabi.newpipe.core.model.Channel

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val player: ExoPlayer,
    private val getStreamDetails: GetStreamDetailsUseCase,
    private val addStreamToHistory: AddStreamToHistoryUseCase,
    private val subscribeToChannel: SubscribeToChannelUseCase
) : ViewModel() {

    private var currentChannel: Channel? = null

    fun prepare(url: String) {
        viewModelScope.launch {
            val result = getStreamDetails(url).first()
            result.onSuccess {
                player.setMediaItem(MediaItem.fromUri(it.url))
                addStreamToHistory(it)
                currentChannel = Channel(
                    url = it.channelUrl ?: it.uploader ?: it.url,
                    name = it.uploader ?: "",
                    avatarUrl = null
                )
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

    fun subscribeToCurrentChannel() {
        val channel = currentChannel ?: return
        viewModelScope.launch { subscribeToChannel(channel) }
    }
}
