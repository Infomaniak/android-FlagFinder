package com.infomaniak.android_flagfinder.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GameViewModel(
    private val gameEngine: GameEngine = GameEngine(),
) : ViewModel() {
    val score = gameEngine.score
    val total = gameEngine.total
    val flags = gameEngine.flags

    val correctFlag = gameEngine.correctFlag
    val answered = gameEngine.answered

    init {
        viewModelScope.launch { gameEngine.initFlagsToGuess() }
    }

    fun select(flag: String) {
        viewModelScope.launch { gameEngine.select(flag) }
    }

    fun restart() {
        viewModelScope.launch { gameEngine.restart() }
    }
}
