package com.example.archive.viewmodels.main

import com.example.model.Hero

data class MainScreenState(
    val heroes: List<Hero> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val isError: Boolean = false,
    val searchQuery: String = ""
)
