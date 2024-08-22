package com.ar.asteroidradar.ui.screens.welcome

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ar.asteroidradar.ui.components.welcome.CurrentWelcomePage
import com.ar.asteroidradar.ui.components.welcome.FinishWelcomeButton
import com.ar.asteroidradar.ui.components.welcome.HorizontalPagerIndicator
import com.ar.asteroidradar.ui.theme.AsteroidRadarAppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(
    onNavigateToHomeScreen: () -> Unit
) {
    val pages = listOf(
        OnBoarding.FirstScreen,
        OnBoarding.SecondScreen,
        OnBoarding.ThirdScreen,
    )
    val pagerState = rememberPagerState(pageCount = { pages.size })

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            CurrentWelcomePage(onBoarding = pages[position])
        }

        HorizontalPagerIndicator(
            numberOfPages = pages.size,
            currentPage = pagerState.currentPage,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
        )

        FinishWelcomeButton(
            onNavigateToHomeScreen = onNavigateToHomeScreen,
            currentPage = pagerState.currentPage
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun WelcomeScreenPreview() {
    AsteroidRadarAppTheme {
        Surface {
            WelcomeScreen(
                onNavigateToHomeScreen = {}
            )
        }
    }
}
