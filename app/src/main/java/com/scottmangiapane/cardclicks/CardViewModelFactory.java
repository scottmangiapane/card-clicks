package com.scottmangiapane.cardclicks;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CardViewModelFactory implements ViewModelProvider.Factory {
    private final int size;
    private final long timeRemaining;

    public CardViewModelFactory(int size, long timeRemaining) {
        this.size = size;
        this.timeRemaining = timeRemaining;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CardViewModel.class)) {
            return (T) new CardViewModel(size, timeRemaining);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
