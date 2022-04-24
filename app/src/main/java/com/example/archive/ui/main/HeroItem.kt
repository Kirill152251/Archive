package com.example.archive.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.model.Hero


@Composable
fun HeroItem(hero: Hero, modifier: Modifier, cardHeight: Float) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.height(cardHeight.dp),
        elevation = 16.dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = hero.image_url,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = cardHeight / 2f
                        )
                    )
            ) {
                Text(
                    text = hero.name,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(4.dp),
                    style = MaterialTheme.typography.h6,
                    fontSize = 16.sp
                )
            }
        }
    }
}

