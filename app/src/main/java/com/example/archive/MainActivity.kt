package com.example.archive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.archive.navigation.BottomBar
import com.example.archive.ui.NavGraphs
import com.example.archive.ui.theme.ArchiveTheme
import com.example.archive.viewmodels.main.MainScreenViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        setContent {
            ArchiveTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomBar(navController = navController) }
                ) {
                    DestinationsNavHost(navGraph = NavGraphs.root, navController = navController)
                }
            }
        }
    }
}
