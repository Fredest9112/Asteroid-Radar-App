package com.ar.asteroidradar.ui.components.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ar.asteroidradar.utils.Constants.LAST_PAGE_HORIZONTAL_PAGER

@Composable
fun FinishWelcomeButton(
    modifier: Modifier = Modifier,
    onClickNavigateToHome: () -> Unit,
    currentPage: Int
){
    //val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    AnimatedVisibility(
        visible = currentPage == LAST_PAGE_HORIZONTAL_PAGER,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 100.dp, end = 100.dp)
    ) {
        Button(onClick = onClickNavigateToHome) {
            Text(text = "Let's Begin")
        }
    }
}

@Preview
@Composable
fun FinishWelcomeButtonPreview(){
    FinishWelcomeButton(
        currentPage = 2,
        onClickNavigateToHome = {}
    )
}