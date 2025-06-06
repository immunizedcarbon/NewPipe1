package org.schabi.newpipe.feature.channel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.schabi.newpipe.core.domain.usecase.GetChannelStreamsUseCase
import org.schabi.newpipe.core.model.Stream

@HiltViewModel
class ChannelViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getChannelStreams: GetChannelStreamsUseCase
) : ViewModel() {
    private val channelUrl: String = checkNotNull(savedStateHandle["channelUrl"])

    private val _channelName = MutableStateFlow("")
    val channelName: StateFlow<String> = _channelName.asStateFlow()

    private val _streams = MutableStateFlow<List<Stream>>(emptyList())
    val streams: StateFlow<List<Stream>> = _streams.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<Throwable?>(null)
    val error: StateFlow<Throwable?> = _error.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            getChannelStreams(channelUrl).collect { result ->
                result.onSuccess { list ->
                    _streams.value = list
                    _channelName.value = list.firstOrNull()?.uploader ?: channelUrl
                    _error.value = null
                    _isLoading.value = false
                }.onFailure { throwable ->
                    _error.value = throwable
                    _isLoading.value = false
                }
            }
        }
    }
}

