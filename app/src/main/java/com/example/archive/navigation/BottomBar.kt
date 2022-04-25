package com.example.archive.navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.archive.ui.appDestination
import com.example.archive.ui.destinations.Destination
import com.example.archive.ui.destinations.DetailsScreenDestination
import com.ramcosta.composedestinations.navigation.navigateTo


@Composable
fun BottomBar(navController: NavController) {
    val currentDestination: Destination? = navController.currentBackStackEntryAsState()
        .value?.appDestination()

    if (currentDestination == DetailsScreenDestination) {
        return
    }

    BottomNavigation(backgroundColor = MaterialTheme.colors.primary) {
        BottomBarDestination.values().forEach { destination ->
            BottomNavigationItem(
                selected = currentDestination == destination.direction,
                onClick = {
                    navController.navigateTo(destination.direction) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = destination.icon),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = destination.label)
                    )
                },
                selectedContentColor = MaterialTheme.colors.onBackground,
                unselectedContentColor = MaterialTheme.colors.background
            )
        }
    }
}