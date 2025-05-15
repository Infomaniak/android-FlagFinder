package com.infomaniak.android_flagfinder.ui.screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.infomaniak.android_flagfinder.ui.theme.AndroidFlagFinderTheme
import com.infomaniak.android_flagfinder.ui.theme.Margin
import com.infomaniak.android_flagfinder.ui.theme.SecondaryText
import com.infomaniak.android_flagfinder.utils.GameUtils

@Composable
fun ColumnScope.Score(score: () -> Int, total: () -> Int) {
    Spacer(Modifier.height(Margin.Medium))
    Text("Score : ${score()}", style = MaterialTheme.typography.headlineLarge)
    Text("n°${total()}/${GameUtils.PartySize}", color = SecondaryText, fontWeight = FontWeight.Medium)
}

@Preview
@Composable
private fun Preview() {
    AndroidFlagFinderTheme {
        Surface {
            Column {
                Score(
                    score = { 1 },
                    total = { 1 },
                )
            }
        }
    }
}
