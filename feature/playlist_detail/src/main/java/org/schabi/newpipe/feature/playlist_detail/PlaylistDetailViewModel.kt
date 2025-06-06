package org.schabi.newpipe.feature.playlist_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map
import org.schabi.newpipe.core.domain.usecase.GetPlaylistItemsUseCase
import org.schabi.newpipe.core.domain.usecase.GetPlaylistsUseCase
import org.schabi.newpipe.core.model.Stream

@HiltViewModel
class PlaylistDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getPlaylistItemsUseCase: GetPlaylistItemsUseCase,
    getPlaylistsUseCase: GetPlaylistsUseCase
) : ViewModel() {
    private val playlistId: Long = checkNotNull(savedStateHandle["playlistId"]).toString().toLong()

    val streams: StateFlow<List<Stream>> =
        getPlaylistItemsUseCase(playlistId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val playlistName: StateFlow<String> =
        getPlaylistsUseCase()
            .map { list -> list.firstOrNull { it.id == playlistId }?.name ?: "" }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")
}
