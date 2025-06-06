package org.schabi.newpipe.feature.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.schabi.newpipe.core.model.Stream
import org.schabi.newpipe.core.ui.StreamItem
import org.schabi.newpipe.core.ui.ShimmeringStreamItem
import org.schabi.newpipe.core.ui.EmptyState
import org.schabi.newpipe.core.ui.ErrorState
import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardActions

@Composable
fun SearchScreen(
    onStreamSelected: (Stream) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState().value
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Search") },
                actions = {
                    IconButton(onClick = { viewModel.executeSearch() }) {
                        Icon(Icons.Filled.Search, contentDescription = "Search")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            OutlinedTextField(
                value = state.query,
                onValueChange = viewModel::onQueryChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { viewModel.executeSearch() })
            )
            when {
                state.isLoading -> {
                    LazyColumn { items(8) { ShimmeringStreamItem() } }
                }
                state.error != null -> {
                    ErrorState(state.error.localizedMessage ?: "Fehler", onRetry = { viewModel.executeSearch() })
                }
                state.results.isEmpty() -> {
                    EmptyState("Keine Ergebnisse")
                }
                else -> {
                    LazyColumn { items(state.results) { stream -> StreamItem(stream, onStreamSelected) } }
                }
            }
        }
    }
}

