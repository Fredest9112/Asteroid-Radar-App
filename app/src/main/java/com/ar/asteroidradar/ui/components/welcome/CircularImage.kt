package com.ar.asteroidradar.ui.components.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ar.asteroidradar.ui.screens.welcome.OnBoarding

@Composable
fun CircularImage(
    onBoarding: OnBoarding = OnBoarding.FirstScreen,
    size: Int = 100
) {
    Image(
        painter = painterResource(id = onBoarding.image),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(all = 20.dp)
            .size(size.dp)
            .clip(shape = CircleShape)
    )
}

@Preview
@Composable
fun CircularImagePreview(){
    CircularImage()
}