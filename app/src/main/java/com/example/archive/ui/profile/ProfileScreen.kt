package com.example.archive.ui.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.archive.R
import com.example.archive.ui.theme.DeepBlue
import com.example.archive.viewmodels.profile.ProfileScreenEvent
import com.example.archive.viewmodels.profile.ProfileViewModel
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.launch

@Composable
@Destination
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            viewModel.onEvent(ProfileScreenEvent.GetImageUri(uri = uri!!))
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopBar(snackbarHostState, viewModel, stringResource(id = R.string.save_msg))
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            bitmap = if (viewModel.state.profilePhotoBitmap == null) {
                context.getDrawable(R.drawable.empty_profile)!!.toBitmap().asImageBitmap()
            } else {
                val bitmapFromStorage = viewModel.state.profilePhotoBitmap
                bitmapFromStorage!!.asImageBitmap()
            },
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(id = R.string.change_profile_picture),
            color = MaterialTheme.colors.onBackground,
            fontSize = 16.sp,
            modifier = Modifier.clickable {
                launcher.launch("image/*")
            }
        )
        Spacer(modifier = Modifier.height(24.dp))

        TextInput(
            hint = stringResource(id = R.string.hint_first_name),
            onValueChange = {
                viewModel.onEvent(ProfileScreenEvent.FirstNameChange(it))
            },
            value = viewModel.state.user.firstName,
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        )
        TextInput(
            hint = stringResource(id = R.string.hint_last_name),
            onValueChange = {
                viewModel.onEvent(ProfileScreenEvent.LastNameChange(it))
            },
            value = viewModel.state.user.lastName,
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        )
        TextInput(
            hint = stringResource(id = R.string.hint_nickname),
            onValueChange = {
                viewModel.onEvent(ProfileScreenEvent.NicknameChange(it))
            },
            value = viewModel.state.user.nickname,
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        )
        SnackbarHost(hostState = snackbarHostState)
    }
}

@Composable
fun TextInput(
    hint: String, onValueChange: (String) -> Unit, value: String, modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        maxLines = 1,
        singleLine = true,
        placeholder = { Text(text = hint) }
    )
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun TopBar(snackbarHostState: SnackbarHostState, viewModel: ProfileViewModel, toast: String) {
    val scope = rememberCoroutineScope()
    TopAppBar(
        backgroundColor = DeepBlue,
        title = {
            Text(
                text = stringResource(id = R.string.profile),
                color = MaterialTheme.colors.onBackground
            )
        },
        actions = {
            Button(
                onClick = {
                    viewModel.onEvent(ProfileScreenEvent.SaveData)
                    scope.launch {
                        snackbarHostState.showSnackbar(toast)
                    }
                }
            ) {
                Text(
                    text = stringResource(id = R.string.save),
                    color = MaterialTheme.colors.onBackground,
                    fontSize = 18.sp
                )
            }
        }
    )
}