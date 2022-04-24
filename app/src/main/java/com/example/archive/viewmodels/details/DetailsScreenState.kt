package com.example.archive.viewmodels.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.model.HeroDetails

data class DetailsScreenState(
    val heroInfo: HeroDetails = HeroDetails(),
    val selectedTabIndex: MutableState<Int> = mutableStateOf(0)
)