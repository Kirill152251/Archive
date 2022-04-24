package com.example.archive.ui.details

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.archive.R
import com.example.archive.ui.theme.Background
import com.example.archive.ui.theme.DeepBlue
import com.example.archive.ui.theme.Purple
import com.example.archive.viewmodels.details.DetailsScreenEvent
import com.example.archive.viewmodels.details.DetailsScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun DetailsScreen(
    navigator: DestinationsNavigator,
    viewModel: DetailsScreenViewModel = hiltViewModel(),
    id: Int
) {
    viewModel.onEvent(DetailsScreenEvent.ShowInfo(id))
    val data = viewModel.state.heroInfo
    val roles = data.roles
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
    ) {
        TopAppBar(
            backgroundColor = DeepBlue,
            title = {
                Text(
                    text = stringResource(id = R.string.toolbar_title_details),
                    color = Color.White
                )
            },
            navigationIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.back_icon),
                    tint = Color.White,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .clickable {
                            navigator.navigateUp()
                        }
                )
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .padding(12.dp)
        ) {
            Card(
                shape = MaterialTheme.shapes.medium
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(142.dp)
                ) {
                    AsyncImage(
                        model = data.imageUrl,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = data.name, style = MaterialTheme.typography.h6, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = data.attackType)
            }
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End,
            ) {
                Image(
                    painter = painterResource(
                        when (data.attribute) {
                            stringResource(id = R.string.agi) -> R.drawable.agility
                            stringResource(id = R.string.str) -> R.drawable.strength
                            else -> R.drawable.intelligence
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .width(48.dp)
                        .height(48.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(12.dp)
                .horizontalScroll(rememberScrollState())
                .fillMaxWidth()

        ) {
            if (roles.isNotEmpty()) {
                for (item in roles) {
                    CreateRole(role = item)
                    Spacer(modifier = Modifier.width(18.dp))
                }
            }
        }
        Column(
            modifier = Modifier
                .padding(12.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            val listOfTitles = listOf(
                stringResource(id = R.string.attack_range),
                stringResource(id = R.string.attack_rate),
                stringResource(id = R.string.move_speed),
                stringResource(id = R.string.base_hp),
                stringResource(id = R.string.base_hp_regen),
                stringResource(id = R.string.base_mana),
                stringResource(id = R.string.base_mana_regen),
                stringResource(id = R.string.base_armor),
                stringResource(id = R.string.base_agi),
                stringResource(id = R.string.base_str),
                stringResource(id = R.string.base_int)
            )
            val listOfValues = listOf(
                data.attackRange,
                data.attackRate,
                data.moveSpeed,
                data.baseHealth,
                data.baseHealthRegen,
                data.baseMana,
                data.baseManaRegen,
                data.baseArmor,
                data.baseAgi,
                data.baseStr,
                data.baseInt
            )
            for (i in listOfTitles.indices) {
                CreateAttributeRow(
                    title = listOfTitles[i],
                    value = listOfValues[i].toString()
                )
            }
        }
    }
}

@Composable
fun CreateAttributeRow(title: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, fontSize = 16.sp)
        Text(text = value, style = MaterialTheme.typography.h6, fontSize = 18.sp)
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun CreateRole(role: String) {
    Box {
        Card(shape = RoundedCornerShape(16.dp), backgroundColor = Purple) {
            Text(
                modifier = Modifier.padding(vertical = 6.dp, horizontal = 16.dp),
                text = role,
                color = Color.White,
                style = MaterialTheme.typography.h6,
                fontSize = 16.sp
            )
        }
    }
}