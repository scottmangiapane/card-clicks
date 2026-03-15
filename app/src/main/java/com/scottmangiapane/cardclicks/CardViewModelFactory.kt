package com.scottmangiapane.cardclicks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CardViewModelFactory(private val size: Int, private val timeRemaining: Long) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardViewModel::class.java)) {
            return CardViewModel(size, timeRemaining) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
