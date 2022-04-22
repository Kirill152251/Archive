package com.example.archive.viewmodels.main_screen

sealed class MainScreenEvent {
    object Refresh: MainScreenEvent()
    object DisplayList: MainScreenEvent()
}