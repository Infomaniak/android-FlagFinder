package com.infomaniak.android_flagfinder.ui.screen.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Card
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.android_flagfinder.ui.theme.AndroidFlagFinderTheme
import com.infomaniak.android_flagfinder.ui.theme.Margin
import com.infomaniak.android_flagfinder.utils.GameUtils

@Composable
fun FlagItem(
    answered: () -> String?,
    flag: String,
    correctFlag: () -> String,
    isNotLastFlag: Boolean,
    selectFlag: (String) -> Unit,
) {
    val resultType = remember(answered()) {
        GameUtils.getResultType(flag, answered(), correctFlag())
    }
    val borderColor by animateColorAsState(resultType.borderColor, label = "$flag border")
    val alpha by animateFloatAsState(resultType.opacity, label = "$flag alpha")

    Card(onClick = { selectFlag(flag) }, border = BorderStroke(4.dp, borderColor)) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .clip(ShapeDefaults.Small)
                .alpha(alpha)
        ) {
            Image(
                painterResource(GameUtils.getFlagDrawable(flag)),
                contentDescription = "flag $flag",
                modifier = Modifier.requiredSize(153.dp, 102.dp),
                contentScale = ContentScale.FillBounds
            )
        }
    }
    if (isNotLastFlag) Spacer(Modifier.height(Margin.Small))
}

@Preview
@Composable
private fun Preview() {
    AndroidFlagFinderTheme {
        Surface {
            Column {
                FlagItem(
                    answered = { "al" },
                    flag = "ar",
                    correctFlag = { "al" },
                    isNotLastFlag = true,
                    selectFlag = {},
                )
                FlagItem(
                    answered = { "dz" },
                    flag = "dz",
                    correctFlag = { "dz" },
                    isNotLastFlag = true,
                    selectFlag = {},
                )
                FlagItem(
                    answered = { "fr" },
                    flag = "fr",
                    correctFlag = { "dz" },
                    isNotLastFlag = false,
                    selectFlag = {},
                )
            }
        }
    }
}
