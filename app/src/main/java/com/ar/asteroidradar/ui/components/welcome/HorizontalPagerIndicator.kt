package com.ar.asteroidradar.ui.components.welcome

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalPagerIndicator(
    numberOfPages: Int,
    modifier: Modifier = Modifier,
    currentPage: Int = 0
){
    LazyRow(
        modifier = modifier
    ) {
        items(numberOfPages) {pageIndex ->
            Canvas(
                modifier = Modifier
                    .padding(all = 10.dp)
                    .size(16.dp)
            ){
                val indicatorColor = if ((pageIndex) == currentPage) Color.Blue else Color.Gray
                drawCircle(
                    color = indicatorColor,
                    radius = size.minDimension / 2,
                    center = Offset(size.width / 2, size.height / 2)
                )
            }
        }
    }
}

@Preview
@Composable
fun HorizontalPagerIndicatorPreview(){
    HorizontalPagerIndicator(numberOfPages = 3, currentPage = 0)
}