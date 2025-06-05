package org.schabi.newpipe.feature.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SettingsScreen(
    onNavigateUp: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Settings") }, navigationIcon = {
                IconButton(onClick = onNavigateUp) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null)
                }
            })
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Text("Appearance")
            var themeMenu by remember { mutableStateOf(false) }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { themeMenu = true }
                    .padding(vertical = 12.dp)
            ) {
                Text("Theme: ${'$'}{state.selectedTheme}")
            }
            DropdownMenu(expanded = themeMenu, onDismissRequest = { themeMenu = false }) {
                listOf("light", "dark", "system").forEach { theme ->
                    DropdownMenuItem(text = { Text(theme) }, onClick = {
                        themeMenu = false
                        viewModel.setTheme(theme)
                    })
                }
            }
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            Text("Content")
            var resMenu by remember { mutableStateOf(false) }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { resMenu = true }
                    .padding(vertical = 12.dp)
            ) {
                Text("Default resolution: ${'$'}{state.defaultResolution}")
            }
            DropdownMenu(expanded = resMenu, onDismissRequest = { resMenu = false }) {
                listOf("best", "720p", "1080p").forEach { res ->
                    DropdownMenuItem(text = { Text(res) }, onClick = {
                        resMenu = false
                        viewModel.setDefaultResolution(res)
                    })
                }
            }
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            Text("History")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Text("Enable watch history", modifier = Modifier.weight(1f))
                Switch(checked = state.watchHistoryEnabled, onCheckedChange = {
                    viewModel.setWatchHistoryEnabled(it)
                })
            }
        }
    }
}
