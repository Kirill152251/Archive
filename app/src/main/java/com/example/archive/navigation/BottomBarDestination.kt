package com.example.archive.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.archive.R
import com.example.archive.ui.destinations.ItemsScreenDestination
import com.example.archive.ui.destinations.MainScreenDestination
import com.example.archive.ui.destinations.ProfileScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec


enum class BottomBarDestination (
    val direction: DirectionDestinationSpec,
    @DrawableRes val icon: Int,
    @StringRes val label: Int
) {
    Main(MainScreenDestination, R.drawable.heroes, R.string.main),
    Items(ItemsScreenDestination, R.drawable.items, R.string.items),
    Profile(ProfileScreenDestination, R.drawable.profile, R.string.profile)
}