package org.schabi.newpipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import org.schabi.newpipe.core.ui.theme.NewPipeTheme
import org.schabi.newpipe.feature.main.MainNavHost

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        const val DEBUG = !BuildConfig.BUILD_TYPE.equals("release")
        const val KEY_IS_IN_BACKGROUND = "is_in_background"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewPipeTheme {
                MainNavHost()
            }
        }
    }
}
