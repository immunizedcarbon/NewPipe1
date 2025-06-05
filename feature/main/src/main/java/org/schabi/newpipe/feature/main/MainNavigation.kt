package org.schabi.newpipe.feature.main

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavType
import org.schabi.newpipe.NewPlayerActivity

@Composable
fun MainNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController, startDestination = "main") {
        composable("main") {
            MainScreen(onStreamSelected = { stream ->
                navController.navigate("player/${stream.url}")
            })
        }
        composable(
            route = "player/{url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: return@composable
            val context = LocalContext.current
            LaunchedEffect(url) {
                context.startActivity(Intent(context, NewPlayerActivity::class.java).apply {
                    putExtra("url", url)
                })
                navController.popBackStack()
            }
        }
    }
}
