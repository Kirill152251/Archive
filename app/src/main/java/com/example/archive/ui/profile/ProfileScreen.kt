package com.example.archive.ui.profile

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.archive.R
import com.example.archive.ui.theme.DeepBlue
import com.example.archive.viewmodels.profile.ProfileScreenEvent
import com.example.archive.viewmodels.profile.ProfileViewModel
import com.example.archive.viewmodels.profile.TypeOfInfo
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DisplayTopBar(context = context, viewModel, stringResource(id = R.string.save_msg))
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.empty_profile),
            contentDescription = null,
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
                
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        DisplayTextInput(
            hint = stringResource(id = R.string.hint_first_name),
            viewModel = viewModel,
            type = TypeOfInfo.FIRST_NAME
        )
        DisplayTextInput(
            hint = stringResource(id = R.string.hint_last_name),
            viewModel = viewModel,
            type = TypeOfInfo.LAST_NAME
        )
        DisplayTextInput(
            hint = stringResource(id = R.string.hint_nickname),
            viewModel = viewModel,
            type = TypeOfInfo.NICKNAME
        )
    }
}

@Composable
fun DisplayTextInput(hint: String, viewModel: ProfileViewModel, type: TypeOfInfo) {
    OutlinedTextField(value = when (type) {
        TypeOfInfo.FIRST_NAME -> viewModel.state.firstName
        TypeOfInfo.LAST_NAME -> viewModel.state.lastName
        TypeOfInfo.NICKNAME -> viewModel.state.nickname
    },
        onValueChange = {
            when (type) {
                TypeOfInfo.FIRST_NAME -> viewModel.onEvent(ProfileScreenEvent.FirstNameChange(it))
                TypeOfInfo.LAST_NAME -> viewModel.onEvent(ProfileScreenEvent.LastNameChange(it))
                TypeOfInfo.NICKNAME -> viewModel.onEvent(ProfileScreenEvent.NicknameChange(it))
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        maxLines = 1,
        singleLine = true,
        placeholder = { Text(text = hint) }
    )
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun DisplayTopBar(context: Context, viewModel: ProfileViewModel, toast: String) {
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
                    Toast.makeText(context, toast, Toast.LENGTH_LONG).show()
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