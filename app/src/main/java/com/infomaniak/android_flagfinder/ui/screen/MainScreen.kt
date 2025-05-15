package com.infomaniak.android_flagfinder.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.infomaniak.android_flagfinder.R
import com.infomaniak.android_flagfinder.ui.screen.components.FlagFinderTopAppBar
import com.infomaniak.android_flagfinder.ui.screen.components.GameView
import com.infomaniak.android_flagfinder.ui.theme.AndroidFlagFinderTheme
import com.infomaniak.android_flagfinder.ui.theme.Margin

@Composable
fun MainScreen(gameViewModel: GameViewModel = viewModel()) {
    val score by gameViewModel.score.collectAsStateWithLifecycle()
    val total by gameViewModel.total.collectAsStateWithLifecycle()
    val flags by gameViewModel.flags.collectAsStateWithLifecycle()
    val correctFlag by gameViewModel.correctFlag.collectAsStateWithLifecycle()
    val answered by gameViewModel.answered.collectAsStateWithLifecycle()

    val canStartGame by remember { derivedStateOf { flags.isNotEmpty() } }

    Scaffold(
        topBar = { FlagFinderTopAppBar(onRestart = gameViewModel::restart) },
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            if (canStartGame) {
                MainContent(
                    score = { score },
                    total = { total },
                    flags = { flags },
                    correctFlag = { correctFlag },
                    answered = { answered },
                    selectFlag = gameViewModel::select,
                )
            }
        }
    }
}

@Composable
private fun MainContent(
    score: () -> Int,
    total: () -> Int,
    flags: () -> List<String>,
    correctFlag: () -> String,
    answered: () -> String?,
    selectFlag: (String) -> Unit,
) {
    Column(Modifier.padding(Margin.Medium)) {
        GameView(score, total, flags, correctFlag, answered, selectFlag)
        Spacer(Modifier.height(Margin.Medium))
        Image(
            painter = painterResource(R.drawable.infomaniak),
            contentDescription = "Infomaniak logo",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@Composable
private fun Preview() {
    AndroidFlagFinderTheme {
        Surface {
            MainContent(
                score = { 1 },
                total = { 12 },
                flags = { listOf("al", "ae", "af") },
                correctFlag = { "al" },
                answered = { "ae" },
                selectFlag = {},
            )
        }
    }
}
