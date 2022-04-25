package com.example.archive.viewmodels.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class ProfileScreenEvent {
    object ShowData : ProfileScreenEvent()
    object SaveData: ProfileScreenEvent()
    data class FirstNameChange(val firstNameChange: String): ProfileScreenEvent()
    data class LastNameChange(val lastNameChange: String): ProfileScreenEvent()
    data class NicknameChange(val nicknameChange: String): ProfileScreenEvent()
}

data class ProfileScreenState(
    val firstName: String = "",
    val lastName: String = "",
    val nickname: String = ""
)

enum class TypeOfInfo{
    FIRST_NAME, LAST_NAME, NICKNAME
}

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    var state by mutableStateOf(ProfileScreenState())

    init {
        onEvent(ProfileScreenEvent.ShowData)
    }

    fun onEvent(event: ProfileScreenEvent) {
        when (event) {
            is ProfileScreenEvent.FirstNameChange -> state = state.copy(firstName = event.firstNameChange)
            is ProfileScreenEvent.LastNameChange -> state = state.copy(lastName = event.lastNameChange)
            is ProfileScreenEvent.NicknameChange -> state = state.copy(nickname = event.nicknameChange)
            ProfileScreenEvent.SaveData -> savaData()
            ProfileScreenEvent.ShowData -> getData()
        }
    }

    private fun savaData() {
        viewModelScope.launch {
            repository.saveFirstName(state.firstName)
            repository.saveLastName(state.lastName)
            repository.saveNickname(state.nickname)
        }
    }

    private fun getData() {
        viewModelScope.launch {
            state = state.copy(
                firstName = repository.getFirstName(),
                lastName = repository.getLastName(),
                nickname = repository.getNickname()
            )
        }
    }
}