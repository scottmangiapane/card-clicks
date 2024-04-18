package com.scottmangiapane.cardclicks;

import androidx.lifecycle.ViewModel;

import com.scottmangiapane.cardclicks.game.Game;

public class CardViewModel extends ViewModel {
    private final int defaultSize;
    private final long defaultTimeRemaining;

    public Game game;
    public boolean isGameRunning;
    public int score;
    public long timeRemaining;

    public CardViewModel(int size, long timeRemaining) {
        this.defaultSize = size;
        this.defaultTimeRemaining = timeRemaining;
        reset();
    }

    public void reset() {
        this.game = new Game(defaultSize);
        this.isGameRunning = true;
        this.score = 0;
        this.timeRemaining = defaultTimeRemaining;
    }
}
