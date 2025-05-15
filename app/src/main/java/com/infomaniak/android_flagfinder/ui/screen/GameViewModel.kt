package com.infomaniak.android_flagfinder.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infomaniak.android_flagfinder.utils.GameUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
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

    init {
        initFlagsToGuess()
    }

    private fun initFlagsToGuess() = viewModelScope.launch {
        if (_total.value >= GameUtils.PartySize) return@launch

        _answered.value = null
        val result = GameUtils.CountryCodes.shuffled().take(3)

        _flags.update { result }
        _correctFlag.value = result.shuffled()[0]
    }

    fun select(flag: String) = viewModelScope.launch {
        if (_answered.value != null) return@launch

        _total.value += 1
        if (flag == _correctFlag.value) {
            _score.value += 1
        }

        _answered.value = flag

        delay(NEXT_GAME_DELAY)
        initFlagsToGuess()
    }

    fun restart() {
        _score.value = 0
        _total.value = 0
        initFlagsToGuess()
    }

    private companion object {
        private const val NEXT_GAME_DELAY = 2_000L
    }
}
