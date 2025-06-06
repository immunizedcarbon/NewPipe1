package org.schabi.newpipe.core.ui.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import org.schabi.newpipe.core.ui.ext.isLight

@Composable
fun NewPipeTheme(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val colorScheme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        if (MaterialTheme.colorScheme.isLight) {
            dynamicLightColorScheme(context)
        } else {
            dynamicDarkColorScheme(context)
        }
    } else {
        MaterialTheme.colorScheme
    }
    MaterialTheme(colorScheme = colorScheme, content = content)
}
