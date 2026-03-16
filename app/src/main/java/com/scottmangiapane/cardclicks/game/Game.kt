package com.scottmangiapane.cardclicks.game

import android.util.Log
import java.util.Locale
import java.util.Random

class Game(size: Int) {
    private val current: Array<Card>
    private val remaining: Array<Card>
    private val generator = Random()

    init {
        val deck = Deck.DECK.toMutableList()
        deck.shuffle()
        current = deck.subList(0, size).toTypedArray()
        remaining = deck.subList(size, deck.size).toTypedArray()
        forceSet()
    }

    private fun forceSet(vararg indexes: Int) {
        if (getSets().isEmpty()) {
            var swapIndex = generator.nextInt(current.size)
            if (indexes.isNotEmpty()) swapIndex = indexes[generator.nextInt(indexes.size)]
            var i1: Int
            var i2: Int
            do {
                i1 = generator.nextInt(current.size)
            } while (i1 == swapIndex)
            do {
                i2 = generator.nextInt(current.size)
            } while (i2 == swapIndex || i2 == i1)
            val newCard = Deck.getComplimentaryCard(current[i1], current[i2])
            setCard(swapIndex, newCard)
            Log.d(
                "Game", String.format(
                    Locale.getDefault(),
                    "Forced set with cards [%d, %d, %d]",
                    swapIndex, i1, i2
                )
            )
        }
    }

    private fun setCard(index: Int, card: Card) {
        val swapIndex = remaining.indexOf(card)
        val temp = current[index]
        current[index] = remaining[swapIndex]
        remaining[swapIndex] = temp
    }

    operator fun get(index: Int): Card {
        return current[index]
    }

    fun getSets(): List<Array<Int>> {
        val sets = mutableListOf<Array<Int>>()
        for (i1 in 0..<current.size - 2) {
            for (i2 in i1 + 1..<current.size - 1) {
                for (i3 in i2 + 1..<current.size) {
                    if (Deck.isSet(current[i1], current[i2], current[i3])) {
                        Log.d(
                            "Game", String.format(
                                Locale.getDefault(),
                                "Found set with cards [%d, %d, %d]",
                                i1, i2, i3
                            )
                        )
                        sets.add(arrayOf(i1, i2, i3))
                    }
                }
            }
        }
        return sets
    }

    fun isSet(i1: Int, i2: Int, i3: Int): Boolean {
        return Deck.isSet(current[i1], current[i2], current[i3])
    }

    fun rotateCards(vararg indexes: Int) {
        shuffleArray(remaining)
        for (i in indexes.indices) setCard(indexes[i], remaining[i])
        forceSet(*indexes)
    }

    companion object {
        private fun <T> shuffleArray(array: Array<T>) {
            val random = Random()
            for (i in array.size - 1 downTo 1) {
                val index = random.nextInt(i + 1)
                val temp = array[index]
                array[index] = array[i]
                array[i] = temp
            }
        }
    }
}
