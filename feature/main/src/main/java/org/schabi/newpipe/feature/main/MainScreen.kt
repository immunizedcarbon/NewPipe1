package org.schabi.newpipe.feature.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.schabi.newpipe.core.model.Stream

@Composable
fun MainScreen(onStreamSelected: (Stream) -> Unit, viewModel: MainViewModel = hiltViewModel()) {
    val state = viewModel.uiState.collectAsState().value
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "NewPipe") }) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(state.trendingStreams) { stream ->
                StreamItem(stream, onStreamSelected)
            }
        }
    }
}

@Composable
fun StreamItem(stream: Stream, onClick: (Stream) -> Unit) {
    Text(
        text = stream.title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick(stream) }
    )
}
