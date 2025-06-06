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
import org.schabi.newpipe.core.domain.usecase.GetPlaylistsUseCase
import org.schabi.newpipe.core.domain.usecase.AddStreamToPlaylistUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.schabi.newpipe.core.model.Channel
import org.schabi.newpipe.core.model.LocalPlaylist

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val player: ExoPlayer,
    private val getStreamDetails: GetStreamDetailsUseCase,
    private val addStreamToHistory: AddStreamToHistoryUseCase,
    private val subscribeToChannel: SubscribeToChannelUseCase,
    getPlaylists: GetPlaylistsUseCase,
    private val addStreamToPlaylist: AddStreamToPlaylistUseCase
) : ViewModel() {
    val playlists: StateFlow<List<LocalPlaylist>> =
        getPlaylists().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private var currentChannel: Channel? = null
    private var currentStream: org.schabi.newpipe.core.model.Stream? = null

    fun prepare(url: String) {
        viewModelScope.launch {
            val result = getStreamDetails(url).first()
            result.onSuccess { stream ->
                currentStream = stream
                player.setMediaItem(MediaItem.fromUri(stream.url))
                addStreamToHistory(stream)
                currentChannel = Channel(
                    url = stream.channelUrl ?: stream.uploader ?: stream.url,
                    name = stream.uploader ?: "",
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

    fun addCurrentStreamToPlaylist(id: Long) {
        val stream = currentStream ?: return
        viewModelScope.launch { addStreamToPlaylist(id, stream) }
    }
}
