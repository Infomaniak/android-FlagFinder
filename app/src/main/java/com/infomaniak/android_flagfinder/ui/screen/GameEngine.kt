package com.infomaniak.android_flagfinder.ui.screen

import com.infomaniak.android_flagfinder.utils.GameUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val NEXT_GAME_DELAY = 1_000L

class GameEngine {

    private val _score = MutableStateFlow(0)
    val score = _score.asStateFlow()

    private val _total = MutableStateFlow(0)
    val total = _total.asStateFlow()

    private val _flags = MutableStateFlow(listOf<String>())
    val flags = _flags.asStateFlow()

    private val _correctFlag = MutableStateFlow("")
    val correctFlag = _correctFlag.asStateFlow()

    private val _answered = MutableStateFlow<String?>(null)
    val answered = _answered.asStateFlow()

    fun initFlagsToGuess() {
        if (_total.value >= GameUtils.PartySize) return

        _answered.update { null }
        val result = GameUtils.CountryCodes.shuffled().take(3)

        _flags.update { result }
        _correctFlag.update { result.shuffled()[0] }
    }

    suspend fun select(flag: String) {
        if (_answered.value != null) return

        _total.value += 1
        if (flag == _correctFlag.value) {
            _score.value += 1
        }

        _answered.update { flag }

        delay(NEXT_GAME_DELAY)
        initFlagsToGuess()
    }

    fun restart() {
        _score.update { 0 }
        _total.update { 0 }
        initFlagsToGuess()
    }
}
