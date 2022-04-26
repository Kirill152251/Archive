package com.example.archive.viewmodels.profile

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.local.InternalStorageManager
import com.example.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class ProfileScreenEvent {
    object ShowData : ProfileScreenEvent()
    object SaveData : ProfileScreenEvent()
    data class GetImageUri(val uri: Uri) : ProfileScreenEvent()
    data class FirstNameChange(val firstNameChange: String) : ProfileScreenEvent()
    data class LastNameChange(val lastNameChange: String) : ProfileScreenEvent()
    data class NicknameChange(val nicknameChange: String) : ProfileScreenEvent()
}

data class ProfileScreenState(
    val user: User = User(),
    val profilePhotoBitmap: Bitmap? = null,
    val profilePhotoUri: Uri? = null
)

data class User(
    val firstName: String = "",
    val lastName: String = "",
    val nickname: String = ""
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository,
    private val internalStorageManager: InternalStorageManager
) : ViewModel() {


    var state by mutableStateOf(ProfileScreenState())

    init {
        onEvent(ProfileScreenEvent.ShowData)
    }

    fun onEvent(event: ProfileScreenEvent) {
        when (event) {
            is ProfileScreenEvent.FirstNameChange -> state =
                state.copy(
                    user = User(
                        firstName = event.firstNameChange,
                        lastName = state.user.lastName,
                        nickname = state.user.nickname
                    )
                )
            is ProfileScreenEvent.LastNameChange -> state =
                state.copy(
                    user = User(
                        firstName = state.user.firstName,
                        lastName = event.lastNameChange,
                        nickname = state.user.nickname
                    )
                )
            is ProfileScreenEvent.NicknameChange -> state =
                state.copy(
                    user = User(
                        firstName = state.user.firstName,
                        lastName = state.user.lastName,
                        nickname = event.nicknameChange
                    )
                )
            ProfileScreenEvent.SaveData -> savaData()
            ProfileScreenEvent.ShowData -> getData()
            is ProfileScreenEvent.GetImageUri -> getImageUri(event.uri)
        }
    }

    private fun getImageUri(uri: Uri) {
        val bitmap = internalStorageManager.transformUriToBitmap(uri)
        state = state.copy(profilePhotoUri = uri, profilePhotoBitmap = bitmap)
    }

    private fun savaData() {
        viewModelScope.launch {
            repository.saveFirstName(state.user.firstName)
            repository.saveLastName(state.user.lastName)
            repository.saveNickname(state.user.nickname)
            if (state.profilePhotoUri != null) {
                internalStorageManager.savePhotoToStorage(state.profilePhotoUri!!)
            }
        }
    }

    private fun getData() {
        viewModelScope.launch {
            state = state.copy(
                user = User(
                    firstName = repository.getFirstName(),
                    lastName = repository.getLastName(),
                    nickname = repository.getNickname()
                ),
                profilePhotoBitmap = if (internalStorageManager.loadPhotoFromStorage().isEmpty()) {
                    null
                } else {
                    internalStorageManager.loadPhotoFromStorage().first()
                }
            )
        }
    }
}