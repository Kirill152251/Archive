package com.example.archive.viewmodels.main

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.local.InternalStorageManager
import com.example.repository.AppRepository
import com.example.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: AppRepository,
    private val internalStorageManager: InternalStorageManager
) : ViewModel() {

    var state by mutableStateOf(MainScreenState())

    val isLoading = mutableStateOf(true)

    private var searchJob: Job? = null

    init {
        onEvent(MainScreenEvent.DisplayList)
        isLoading.value = false
    }

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.DisplayList -> getData()
            is MainScreenEvent.Refresh -> getData(true)
            is MainScreenEvent.SearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500)
                    getData()
                }
            }
        }
    }

    private fun getData(
        isRefreshNeeded: Boolean = false,
        query: String = state.searchQuery.lowercase()
    ) {
        viewModelScope.launch {
            repository.getHeroesList(isRefreshNeeded, query).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            state = state.copy(heroes = it)
                        }
                    }
                    is Resource.Error -> {
                        state = state.copy(isError = true)
                    }
                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }
                }
            }
        }
    }
}