package org.schabi.newpipe.feature.playlists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.schabi.newpipe.core.domain.usecase.CreatePlaylistUseCase
import org.schabi.newpipe.core.domain.usecase.GetPlaylistsUseCase
import org.schabi.newpipe.core.model.LocalPlaylist

@HiltViewModel
class PlaylistsViewModel @Inject constructor(
    private val getPlaylists: GetPlaylistsUseCase,
    private val createPlaylist: CreatePlaylistUseCase
) : ViewModel() {
    val playlists: StateFlow<List<LocalPlaylist>> =
        getPlaylists().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun create(name: String) {
        viewModelScope.launch { createPlaylist(name) }
    }
}
