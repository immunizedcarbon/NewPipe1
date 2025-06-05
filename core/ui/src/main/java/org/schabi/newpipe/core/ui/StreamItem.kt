package org.schabi.newpipe.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.schabi.newpipe.core.model.Stream

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
