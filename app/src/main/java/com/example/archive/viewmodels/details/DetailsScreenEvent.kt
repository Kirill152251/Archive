package com.example.archive.viewmodels.details

sealed class DetailsScreenEvent{
    data class ShowInfo(val id: Int): DetailsScreenEvent()
}


