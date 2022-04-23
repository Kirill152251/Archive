package com.example.archive.viewmodels.main_screen

sealed class MainScreenEvent {
    object Refresh: MainScreenEvent()
    object DisplayList: MainScreenEvent()
    data class SearchQueryChange(val query: String): MainScreenEvent()
}