package com.ar.asteroidradar.ui.components.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ar.asteroidradar.domain.states.AsteroidTimeState
import com.ar.asteroidradar.ui.theme.AsteroidRadarAppTheme
import com.ar.asteroidradar.utils.Constants.DATE_OPTIONS

@Composable
fun DateChooser(
    onOptionSelected: (String) -> Unit,
    selectedOption: AsteroidTimeState
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        DATE_OPTIONS.forEach { timeOption ->
            Column(
                modifier = Modifier
                    .height(100.dp)
                    .selectable(
                        onClick = { onOptionSelected(timeOption) },
                        selected = (timeOption == selectedOption.dateState),
                        role = Role.RadioButton
                    )
                    .padding(all = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                RadioButton(
                    selected = (timeOption == selectedOption.dateState),
                    onClick = null
                )
                Text(
                    text = timeOption,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
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
fun DateChooserPreview() {
    AsteroidRadarAppTheme {
        Surface {
            DateChooser(
                onOptionSelected = {},
                selectedOption = AsteroidTimeState.TODAY
            )
        }
    }
}