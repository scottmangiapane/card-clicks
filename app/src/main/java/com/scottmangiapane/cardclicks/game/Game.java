package com.scottmangiapane.cardclicks.game;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Game {
    private final Card[] current;
    private final Card[] remaining;
    private final Random generator = new Random();

    public Game(int size) {
        List<Card> deck = new ArrayList<>(Arrays.asList(Deck.DECK));
        Collections.shuffle(deck);
        current = deck.subList(0, size).toArray(new Card[0]);
        remaining = deck.subList(size, deck.size()).toArray(new Card[0]);
        forceSet();
    }

    private void forceSet(int... indexes) {
        if (getSets().isEmpty()) {
            int swapIndex = generator.nextInt(current.length);
            if (indexes.length > 0) {
                swapIndex = indexes[generator.nextInt(indexes.length)];
            }
            int i1, i2;
            do {
                i1 = generator.nextInt(current.length);
            } while (i1 == swapIndex);
            do {
                i2 = generator.nextInt(current.length);
            } while (i2 == swapIndex || i2 == i1);
            Card newCard = Deck.getComplimentaryCard(current[i1], current[i2]);
            setCard(swapIndex, newCard);
            Log.d("Game", String.format(
                    Locale.getDefault(),
                    "Forced set with cards [%d, %d, %d]",
                    swapIndex, i1, i2));
        }
    }

    private void setCard(int index, Card card) {
        int swapIndex = Arrays.asList(remaining).indexOf(card);
        Card temp = current[index];
        current[index] = remaining[swapIndex];
        remaining[swapIndex] = temp;
    }

    public Card get(int index) {
        return current[index];
    }

    public List<Integer[]> getSets() {
        List<Integer[]> sets = new ArrayList<>();
        for (int i1 = 0; i1 < current.length - 2; i1++) {
            for (int i2 = i1 + 1; i2 < current.length - 1; i2++) {
                for (int i3 = i2 + 1; i3 < current.length; i3++) {
                    if (Deck.isSet(current[i1], current[i2], current[i3])) {
                        Log.d("Game", String.format(
                                Locale.getDefault(),
                                "Found set with cards [%d, %d, %d]",
                                i1, i2, i3));
                        Integer[] set = new Integer[]{i1, i2, i3};
                        sets.add(set);
                    }
                }
            }
        }
        return sets;
    }

    public boolean isSet(int i1, int i2, int i3) {
        return Deck.isSet(current[i1], current[i2], current[i3]);
    }

    public void rotateCards(int... indexes) {
        shuffleArray(remaining);
        for (int i = 0; i < indexes.length; i++) {
            setCard(indexes[i], remaining[i]);
        }
        forceSet(indexes);
    }

    private static <T> void shuffleArray(T[] array) {
        int index;
        T temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public int size() {
        return current.length;
    }
}
