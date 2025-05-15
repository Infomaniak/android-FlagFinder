package com.infomaniak.android_flagfinder.ui.screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.infomaniak.android_flagfinder.R
import com.infomaniak.android_flagfinder.ui.theme.AndroidFlagFinderTheme
import com.infomaniak.android_flagfinder.ui.theme.Margin
import com.infomaniak.android_flagfinder.ui.theme.SecondaryText
import com.infomaniak.android_flagfinder.utils.GameUtils

@Composable
fun GameView(
    score: () -> Int,
    total: () -> Int,
    flags: () -> List<String>,
    correctFlag: () -> String,
    answered: () -> String?,
    selectFlag: (String) -> Unit,
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Margin.Medium),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            GameHeader(correctFlag)

            Spacer(Modifier.height(Margin.Medium))

            flags().forEachIndexed { index, flag ->
                FlagItem(answered, flag, correctFlag, isNotLastFlag = index != flags().lastIndex, selectFlag)
            }

            Score(score, total)
        }
    }
}

@Composable
fun GameHeader(correctFlag: () -> String) {
    Text(
        stringResource(R.string.find_the_flag),
        color = SecondaryText,
        fontWeight = FontWeight.Medium,
        style = MaterialTheme.typography.bodyLarge
    )
    Spacer(modifier = Modifier.height(Margin.Small))
    Text(GameUtils.getCountryName(correctFlag()), style = MaterialTheme.typography.headlineLarge)
}

@Preview
@Composable
private fun Preview() {
    AndroidFlagFinderTheme {
        Surface {
            GameView(
                score = { 1 },
                total = { 12 },
                flags = { listOf("ar", "fr", "al") },
                correctFlag = { "al" },
                answered = { "fr" },
                selectFlag = {},
            )
        }
    }
}
