package org.schabi.newpipe.feature.playlist_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.schabi.newpipe.core.domain.usecase.GetPlaylistItemsUseCase
import org.schabi.newpipe.core.domain.usecase.GetPlaylistsUseCase
import org.schabi.newpipe.core.model.Stream

@HiltViewModel
class PlaylistDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPlaylistItemsUseCase: GetPlaylistItemsUseCase,
    getPlaylistsUseCase: GetPlaylistsUseCase
) : ViewModel() {
    private val playlistId: Long = checkNotNull(savedStateHandle["playlistId"]).toString().toLong()

    private val _streams = MutableStateFlow<List<Stream>>(emptyList())
    val streams: StateFlow<List<Stream>> = _streams.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    val playlistName: StateFlow<String> =
        getPlaylistsUseCase()
            .map { list -> list.firstOrNull { it.id == playlistId }?.name ?: "" }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

    init {
        viewModelScope.launch {
            getPlaylistItemsUseCase(playlistId).collect { list ->
                _streams.value = list
                _isLoading.value = false
            }
        }
    }
}

