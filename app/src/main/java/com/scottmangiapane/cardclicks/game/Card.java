package com.scottmangiapane.cardclicks.game;

public class Card {
    public final int drawable;
    public final Color color;
    public final Number number;
    public final Shape shape;
    public final Type type;

    public Card(int drawable, Color color, Number number, Shape shape, Type type) {
        this.drawable = drawable;
        this.color = color;
        this.number = number;
        this.shape = shape;
        this.type = type;
    }
}
