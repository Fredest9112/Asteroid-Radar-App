package com.ar.asteroidradar.ui.components.error

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.delay

@Composable
fun ToastError(
    isThereAnError: Boolean,
    message: String,
    onErrorMessageShown: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxSize()) {
        AnimatedVisibility(
            visible = isThereAnError,
            modifier = Modifier.align(Alignment.BottomStart),
            enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
            exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
        ) {
            Box(
                modifier = Modifier
                    .zIndex(1f)
                    .background(
                        color = MaterialTheme.colorScheme.onError,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = message,
                    color = Color.White,
                    minLines = 1
                )
            }
        }

        if (isThereAnError) {
            LaunchedEffect(key1 = isThereAnError) {
                delay(4000)
                onErrorMessageShown()
            }
        }
    }
}
