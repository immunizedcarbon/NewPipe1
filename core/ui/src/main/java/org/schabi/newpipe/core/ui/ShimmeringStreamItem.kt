package org.schabi.newpipe.core.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme

@Composable
fun ShimmeringStreamItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .width(120.dp)
                .height(72.dp)
                .shimmer()
                .background(MaterialTheme.colorScheme.surfaceVariant)
        )
        Spacer(Modifier.width(8.dp))
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            Box(
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth(0.7f)
                    .shimmer()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )
            Spacer(Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .height(14.dp)
                    .fillMaxWidth(0.5f)
                    .shimmer()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )
        }
    }
}

