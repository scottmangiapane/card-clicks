package com.scottmangiapane.cardcliques;

public class Clique {
    public Card[][] cards;

    public Clique() {
        cards = new Card[4][3];
        for (int i1 = 0; i1 < height(); i1++)
            for (int i2 = 0; i2 < width(); i2++)
                cards[i1][i2] = new Card();
    }

    public int height() {
        return cards.length;
    }

    public int width() {
        return cards[0].length;
    }

    public boolean isCardDisplayed(int drawable, int d1, int d2) {
        for (int i1 = 0; i1 < cards.length; i1++)
            for (int i2 = 0; i2 < cards[0].length; i2++)
                if ((cards[i1][i2].drawable == drawable) && (i1 != d1 || i2 != d2))
                    return true;
        return false;
    }

    public boolean isSet(int card1x, int card1y, int card2x, int card2y, int card3x, int card3y) {
        if (
                isSetColor(card1x, card1y, card2x, card2y, card3x, card3y)
                        && isSetNumber(card1x, card1y, card2x, card2y, card3x, card3y)
                        && isSetShape(card1x, card1y, card2x, card2y, card3x, card3y)
                        && isSetType(card1x, card1y, card2x, card2y, card3x, card3y)
                )
            return true;
        return false;
    }

    public boolean isSetColor(int card1x, int card1y, int card2x,
                              int card2y, int card3x, int card3y) {
        if (cards[card1x][card1y].color.equals(cards[card2x][card2y].color)
                && cards[card2x][card2y].color.equals(cards[card3x][card3y].color))
            return true;
        if (!cards[card1x][card1y].color.equals(cards[card2x][card2y].color)
                && !cards[card2x][card2y].color.equals(cards[card3x][card3y].color)
                && !cards[card1x][card1y].color.equals(cards[card3x][card3y].color))
            return true;
        return false;
    }

    public boolean isSetNumber(int card1x, int card1y, int card2x,
                               int card2y, int card3x, int card3y) {
        if (cards[card1x][card1y].number == (cards[card2x][card2y].number)
                && cards[card2x][card2y].number == (cards[card3x][card3y].number))
            return true;
        if (cards[card1x][card1y].number != (cards[card2x][card2y].number)
                && cards[card2x][card2y].number != (cards[card3x][card3y].number)
                && cards[card1x][card1y].number != (cards[card3x][card3y].number))
            return true;
        return false;
    }

    public boolean isSetShape(int card1x, int card1y,
                              int card2x, int card2y,
                              int card3x, int card3y) {
        if (cards[card1x][card1y].shape.equals(cards[card2x][card2y].shape)
                && cards[card2x][card2y].shape.equals(cards[card3x][card3y].shape))
            return true;
        if (!cards[card1x][card1y].shape.equals(cards[card2x][card2y].shape)
                && !cards[card2x][card2y].shape.equals(cards[card3x][card3y].shape)
                && !cards[card1x][card1y].shape.equals(cards[card3x][card3y].shape))
            return true;
        return false;
    }

    public boolean isSetType(int card1x, int card1y,
                             int card2x, int card2y,
                             int card3x, int card3y) {
        if (cards[card1x][card1y].type.equals(cards[card2x][card2y].type)
                && cards[card2x][card2y].type.equals(cards[card3x][card3y].type))
            return true;
        if (!cards[card1x][card1y].type.equals(cards[card2x][card2y].type)
                && !cards[card2x][card2y].type.equals(cards[card3x][card3y].type)
                && !cards[card1x][card1y].type.equals(cards[card3x][card3y].type))
            return true;
        return false;
    }

    public int numberOfSetsDisplayed() {
        int count = 0;
        int[] row = {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3};
        int[] column = {0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2};
        for (int i1 = 0; i1 < 12; i1++)
            for (int i2 = Math.min(i1 + 1, 12); i2 < 12; i2++)
                for (int i3 = Math.min(i2 + 1, 12); i3 < 12; i3++)
                    if (isSet(row[i1], column[i1], row[i2], column[i2], row[i3], column[i3]))
                        count++;
        return count;
    }
}
