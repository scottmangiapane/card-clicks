package com.scottmangiapane.cardclicks.game

@JvmRecord
data class Card(
    val drawable: Int,
    @JvmField val color: Color,
    @JvmField val number: Number,
    @JvmField val shape: Shape,
    @JvmField val type: Type
)
