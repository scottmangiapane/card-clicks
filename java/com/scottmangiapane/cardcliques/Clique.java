package com.scottmangiapane.cardcliques;

public class Clique {
    public Card[][] cards;

    public Clique(int height, int width) {
        cards = new Card[height][width];
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
        int[] row = new int[width() * height()];
        int[] column = new int[width() * height()];
        for (int i = 0; i < row.length; i++)
            row[i] = i / width();
        for (int i = 0; i < column.length; i++)
            column[i] = i % width();
        for (int i1 = 0; i1 < width() * height(); i1++)
            for (int i2 = Math.min(i1 + 1, width() * height()); i2 < width() * height(); i2++)
                for (int i3 = Math.min(i2 + 1, width() * height()); i3 < width() * height(); i3++)
                    if (isSet(row[i1], column[i1], row[i2], column[i2], row[i3], column[i3]))
                        count++;
        return count;
    }

    public void setNewCard(int i1, int i2) {
        do {
            cards[i1][i2].drawable = (int) (Math.random() * (R.drawable.deck_squiggle_red_striped_1
                    - R.drawable.deck_diamond_blue_border_1))
                    + R.drawable.deck_diamond_blue_border_1;
            refreshValue(i1, i2);
        } while (isCardDisplayed(cards[i1][i2].drawable, i1, i2));
    }

    public void setNewCardCheckSetPossible(int i1, int i2) {
        do {
            cards[i1][i2].drawable = (int) (Math.random() * (R.drawable.deck_squiggle_red_striped_1
                    - R.drawable.deck_diamond_blue_border_1))
                    + R.drawable.deck_diamond_blue_border_1;
            refreshValue(i1, i2);
        }
        while (isCardDisplayed(cards[i1][i2].drawable, i1, i2) || numberOfSetsDisplayed() == 0);
    }

    public void refreshValue(int i1, int i2) {
        switch (cards[i1][i2].drawable) {
            case 0:
                cards[i1][i2].shape = "";
                cards[i1][i2].color = "";
                cards[i1][i2].type = "";
                cards[i1][i2].number = 0;
                break;
            case R.drawable.deck_diamond_blue_border_1:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_diamond_blue_border_2:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_diamond_blue_border_3:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_diamond_blue_solid_1:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_diamond_blue_solid_2:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_diamond_blue_solid_3:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_diamond_blue_striped_1:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_diamond_blue_striped_2:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_diamond_blue_striped_3:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_diamond_green_border_1:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_diamond_green_border_2:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_diamond_green_border_3:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_diamond_green_solid_1:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_diamond_green_solid_2:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_diamond_green_solid_3:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_diamond_green_striped_1:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_diamond_green_striped_2:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_diamond_green_striped_3:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_diamond_red_border_1:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_diamond_red_border_2:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_diamond_red_border_3:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_diamond_red_solid_1:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_diamond_red_solid_2:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_diamond_red_solid_3:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_diamond_red_striped_1:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_diamond_red_striped_2:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_diamond_red_striped_3:
                cards[i1][i2].shape = "diamond";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_oval_blue_border_1:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_oval_blue_border_2:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_oval_blue_border_3:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_oval_blue_solid_1:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_oval_blue_solid_2:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_oval_blue_solid_3:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_oval_blue_striped_1:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_oval_blue_striped_2:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_oval_blue_striped_3:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_oval_green_border_1:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_oval_green_border_2:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_oval_green_border_3:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_oval_green_solid_1:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_oval_green_solid_2:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_oval_green_solid_3:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_oval_green_striped_1:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_oval_green_striped_2:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_oval_green_striped_3:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_oval_red_border_1:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_oval_red_border_2:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_oval_red_border_3:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_oval_red_solid_1:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_oval_red_solid_2:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_oval_red_solid_3:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_oval_red_striped_1:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_oval_red_striped_2:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_oval_red_striped_3:
                cards[i1][i2].shape = "oval";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_squiggle_blue_border_1:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_squiggle_blue_border_2:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_squiggle_blue_border_3:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_squiggle_blue_solid_1:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_squiggle_blue_solid_2:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_squiggle_blue_solid_3:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_squiggle_blue_striped_1:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_squiggle_blue_striped_2:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_squiggle_blue_striped_3:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "blue";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_squiggle_green_border_1:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_squiggle_green_border_2:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_squiggle_green_border_3:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_squiggle_green_solid_1:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_squiggle_green_solid_2:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_squiggle_green_solid_3:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_squiggle_green_striped_1:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_squiggle_green_striped_2:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_squiggle_green_striped_3:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "green";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_squiggle_red_border_1:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_squiggle_red_border_2:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_squiggle_red_border_3:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "border";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_squiggle_red_solid_1:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_squiggle_red_solid_2:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_squiggle_red_solid_3:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "solid";
                cards[i1][i2].number = 3;
                break;
            case R.drawable.deck_squiggle_red_striped_1:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 1;
                break;
            case R.drawable.deck_squiggle_red_striped_2:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 2;
                break;
            case R.drawable.deck_squiggle_red_striped_3:
                cards[i1][i2].shape = "squiggle";
                cards[i1][i2].color = "red";
                cards[i1][i2].type = "striped";
                cards[i1][i2].number = 3;
                break;
        }
    }

    public void refreshAllValues() {
        for (int i1 = 0; i1 < height(); i1++)
            for (int i2 = 0; i2 < width(); i2++) {
                refreshValue(i1, i2);
            }
    }
}
