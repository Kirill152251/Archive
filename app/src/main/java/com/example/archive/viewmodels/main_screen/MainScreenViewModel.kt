package com.example.archive.viewmodels.main_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repository.MainScreenRepository
import com.example.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: MainScreenRepository
) : ViewModel() {

    var state by mutableStateOf(MainScreenState())

    init {
        onEvent(MainScreenEvent.DisplayList)
    }

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.DisplayList -> getData(false)
            is MainScreenEvent.Refresh -> getData(true)
        }
    }

    private fun getData(isRefreshNeeded: Boolean) {
        viewModelScope.launch {
            repository.getHeroesList(isRefreshNeeded).collect { result ->
                when(result) {
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