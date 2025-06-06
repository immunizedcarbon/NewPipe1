package org.schabi.newpipe.core.ui.ext

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.luminance

/**
 * True, wenn das aktuelle ColorScheme hell ist (Surface-Luminanz > 0.5).
 */
val ColorScheme.isLight: Boolean
    get() = surface.luminance() > 0.5f
