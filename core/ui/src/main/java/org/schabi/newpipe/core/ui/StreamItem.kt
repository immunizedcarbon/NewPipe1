package org.schabi.newpipe.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.schabi.newpipe.core.model.Stream

@Composable
fun StreamItem(stream: Stream, onClick: (Stream) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(stream) }
            .padding(8.dp)
    ) {
        AsyncImage(
            model = stream.thumbnailUrl,
            contentDescription = null,
            modifier = Modifier
                .width(120.dp)
                .height(72.dp)
        )
        Spacer(Modifier.width(8.dp))
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            Text(text = stream.title)
            stream.uploader?.let { Text(text = it) }
        }
    }
}
