package com.ar.asteroidradar.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ar.asteroidradar.ui.theme.AsteroidRadarAppTheme

@Composable
fun AsteroidMainData(
    modifier: Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        Column {
            Text(text = "Name of the asteroid")
            Text(text = "Date of the asteroid")
        }
        //Spacer(modifier = modifier.width(100.dp))
        Text(text = "Emoji")
    }
}

@Preview(showBackground = true)
@Composable
fun AsteroidMainDataPreview(){
    AsteroidRadarAppTheme {
        AsteroidMainData(modifier = Modifier)
    }
}
