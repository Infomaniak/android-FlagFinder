package com.infomaniak.android_flagfinder.data

import androidx.compose.ui.graphics.Color
import com.infomaniak.android_flagfinder.ui.theme.ErrorFlagColor
import com.infomaniak.android_flagfinder.ui.theme.SuccessFlagColor

enum class AnimationType(val opacity: Float, val borderColor: Color) {
    CORRECT(1f, SuccessFlagColor),
    WRONG(1f, ErrorFlagColor),
    BLURRED(.6f, Color.Transparent),
    NONE(1f, Color.Transparent),
}
