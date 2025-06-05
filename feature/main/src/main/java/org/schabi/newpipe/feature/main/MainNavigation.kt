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
import org.schabi.newpipe.feature.history.HistoryScreen
import org.schabi.newpipe.core.ui.components.MainTab

@Composable
fun MainNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController, startDestination = MainTab.Trends.route) {
        composable(MainTab.Trends.route) {
            MainScreen(
                onStreamSelected = { stream -> navController.navigate("player/${stream.url}") },
                selectedTab = MainTab.Trends,
                onTabSelected = { tab -> if (tab != MainTab.Trends) navController.navigate(tab.route) }
            )
        }
        composable(MainTab.History.route) {
            HistoryScreen(
                onStreamSelected = { stream -> navController.navigate("player/${stream.url}") },
                selectedTab = MainTab.History,
                onTabSelected = { tab -> if (tab != MainTab.History) navController.navigate(tab.route) }
            )
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
