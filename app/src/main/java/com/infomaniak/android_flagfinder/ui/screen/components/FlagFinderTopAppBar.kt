package com.infomaniak.android_flagfinder.ui.screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infomaniak.android_flagfinder.R
import com.infomaniak.android_flagfinder.ui.theme.AndroidFlagFinderTheme
import com.infomaniak.android_flagfinder.ui.theme.Margin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlagFinderTopAppBar(onRestart: () -> Unit) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(shape = ShapeDefaults.Small) {
                    Icon(painterResource(R.drawable.flag), "flag", modifier = Modifier.padding(8.dp))
                }
                Spacer(Modifier.width(Margin.Small))
                Text(stringResource(R.string.app_name))
            }
        },
        actions = {
            OutlinedButton(onClick = onRestart) {
                Icon(Icons.Default.Refresh, "Refresh icon button")
                Text(stringResource(R.string.restart))
            }
        }
    )
}

@Preview
@Composable
private fun Preview() {
    AndroidFlagFinderTheme {
        Surface {
            FlagFinderTopAppBar(onRestart = {})
        }
    }
}
