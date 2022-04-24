package com.example.archive.viewmodels.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    var state by mutableStateOf(DetailsScreenState())

    fun onEvent(event: DetailsScreenEvent) {
        when (event) {
            is DetailsScreenEvent.ShowInfo -> getHeroData(event.id)
        }
    }

    private fun getHeroData(id: Int) {
        viewModelScope.launch {
            repository.getHeroDetails(id).collect {
                state = state.copy(heroInfo = it)
            }
        }
    }
}