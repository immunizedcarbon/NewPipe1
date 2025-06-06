package org.schabi.newpipe.feature.playlists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.schabi.newpipe.core.domain.usecase.CreatePlaylistUseCase
import org.schabi.newpipe.core.domain.usecase.GetPlaylistsUseCase
import org.schabi.newpipe.core.model.LocalPlaylist

@HiltViewModel
class PlaylistsViewModel @Inject constructor(
    private val getPlaylists: GetPlaylistsUseCase,
    private val createPlaylist: CreatePlaylistUseCase
) : ViewModel() {
    private val _playlists = MutableStateFlow<List<LocalPlaylist>>(emptyList())
    val playlists: StateFlow<List<LocalPlaylist>> = _playlists.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            getPlaylists().collect { list ->
                _playlists.value = list
                _isLoading.value = false
            }
        }
    }

    fun create(name: String) {
        viewModelScope.launch { createPlaylist(name) }
    }
}

