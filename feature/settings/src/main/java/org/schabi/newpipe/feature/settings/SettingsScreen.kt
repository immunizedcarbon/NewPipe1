package org.schabi.newpipe.feature.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel(), onBack: () -> Unit = {}) {
    val watchHistoryEnabled = viewModel.watchHistoryEnabled.collectAsState().value
    val theme = viewModel.theme.collectAsState().value
    val resolution = viewModel.defaultResolution.collectAsState().value

    var themeDialog by remember { mutableStateOf(false) }
    var resDialog by remember { mutableStateOf(false) }

    Scaffold(topBar = { TopAppBar(title = { Text("Settings") }) }) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            Text("Appearance", style = MaterialTheme.typography.titleMedium)
            Column(Modifier.fillMaxWidth().clickable { themeDialog = true }.padding(vertical = 12.dp)) {
                Text("Theme")
                Text(theme, modifier = Modifier.align(Alignment.End))
            }
            if (themeDialog) {
                AlertDialog(onDismissRequest = { themeDialog = false }, confirmButton = {}, title = { Text("Theme") }) {
                    Column(Modifier.padding(8.dp)) {
                        listOf("light", "dark", "black", "auto").forEach { option ->
                            RowItem(option == theme, option) { viewModel.setTheme(option); themeDialog = false }
                        }
                    }
                }
            }
            Spacer(Modifier.padding(8.dp))
            Text("Content", style = MaterialTheme.typography.titleMedium)
            Column(Modifier.fillMaxWidth().clickable { resDialog = true }.padding(vertical = 12.dp)) {
                Text("Default resolution")
                Text(resolution, modifier = Modifier.align(Alignment.End))
            }
            if (resDialog) {
                AlertDialog(onDismissRequest = { resDialog = false }, confirmButton = {}, title = { Text("Default resolution") }) {
                    Column(Modifier.padding(8.dp)) {
                        listOf("best", "1080p", "720p60", "720p", "480p").forEach { option ->
                            RowItem(option == resolution, option) { viewModel.setDefaultResolution(option); resDialog = false }
                        }
                    }
                }
            }
            Spacer(Modifier.padding(8.dp))
            Text("History", style = MaterialTheme.typography.titleMedium)
            RowItemSwitch("Watch history", watchHistoryEnabled) { viewModel.setWatchHistoryEnabled(it) }
        }
    }
}

@Composable
private fun RowItem(selected: Boolean, text: String, onClick: () -> Unit) {
    androidx.compose.foundation.layout.Row(
        Modifier.fillMaxWidth().clickable { onClick() }.padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = onClick)
        Text(text, modifier = Modifier.padding(start = 8.dp))
    }
}

@Composable
private fun RowItemSwitch(text: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    androidx.compose.foundation.layout.Row(
        Modifier.fillMaxWidth().padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text)
        Spacer(Modifier.weight(1f))
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}
