package com.scottmangiapane.cardclicks

import androidx.lifecycle.ViewModel
import com.scottmangiapane.cardclicks.game.Game

class CardViewModel(private val defaultSize: Int, private val defaultTimeRemaining: Long) :
    ViewModel() {
    lateinit var game: Game
    var isGameRunning = false
    var score = 0
    var timeRemaining: Long = 0

    init {
        reset()
    }

    fun reset() {
        game = Game(defaultSize)
        isGameRunning = true
        score = 0
        timeRemaining = defaultTimeRemaining
    }
}
