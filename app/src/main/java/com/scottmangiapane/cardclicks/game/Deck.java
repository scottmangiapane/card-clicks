package com.scottmangiapane.cardclicks.game;

import com.scottmangiapane.cardclicks.R;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

public class Deck {
    public static final Card[] DECK = new Card[] {
            new Card(R.drawable.deck_diamond_blue_border_1, Color.BLUE, Number.ONE, Shape.DIAMOND, Type.BORDER),
            new Card(R.drawable.deck_diamond_blue_border_2, Color.BLUE, Number.TWO, Shape.DIAMOND, Type.BORDER),
            new Card(R.drawable.deck_diamond_blue_border_3, Color.BLUE, Number.THREE, Shape.DIAMOND, Type.BORDER),
            new Card(R.drawable.deck_diamond_blue_solid_1, Color.BLUE, Number.ONE, Shape.DIAMOND, Type.SOLID),
            new Card(R.drawable.deck_diamond_blue_solid_2, Color.BLUE, Number.TWO, Shape.DIAMOND, Type.SOLID),
            new Card(R.drawable.deck_diamond_blue_solid_3, Color.BLUE, Number.THREE, Shape.DIAMOND, Type.SOLID),
            new Card(R.drawable.deck_diamond_blue_striped_1, Color.BLUE, Number.ONE, Shape.DIAMOND, Type.STRIPED),
            new Card(R.drawable.deck_diamond_blue_striped_2, Color.BLUE, Number.TWO, Shape.DIAMOND, Type.STRIPED),
            new Card(R.drawable.deck_diamond_blue_striped_3, Color.BLUE, Number.THREE, Shape.DIAMOND, Type.STRIPED),
            new Card(R.drawable.deck_diamond_green_border_1, Color.GREEN, Number.ONE, Shape.DIAMOND, Type.BORDER),
            new Card(R.drawable.deck_diamond_green_border_2, Color.GREEN, Number.TWO, Shape.DIAMOND, Type.BORDER),
            new Card(R.drawable.deck_diamond_green_border_3, Color.GREEN, Number.THREE, Shape.DIAMOND, Type.BORDER),
            new Card(R.drawable.deck_diamond_green_solid_1, Color.GREEN, Number.ONE, Shape.DIAMOND, Type.SOLID),
            new Card(R.drawable.deck_diamond_green_solid_2, Color.GREEN, Number.TWO, Shape.DIAMOND, Type.SOLID),
            new Card(R.drawable.deck_diamond_green_solid_3, Color.GREEN, Number.THREE, Shape.DIAMOND, Type.SOLID),
            new Card(R.drawable.deck_diamond_green_striped_1, Color.GREEN, Number.ONE, Shape.DIAMOND, Type.STRIPED),
            new Card(R.drawable.deck_diamond_green_striped_2, Color.GREEN, Number.TWO, Shape.DIAMOND, Type.STRIPED),
            new Card(R.drawable.deck_diamond_green_striped_3, Color.GREEN, Number.THREE, Shape.DIAMOND, Type.STRIPED),
            new Card(R.drawable.deck_diamond_red_border_1, Color.RED, Number.ONE, Shape.DIAMOND, Type.BORDER),
            new Card(R.drawable.deck_diamond_red_border_2, Color.RED, Number.TWO, Shape.DIAMOND, Type.BORDER),
            new Card(R.drawable.deck_diamond_red_border_3, Color.RED, Number.THREE, Shape.DIAMOND, Type.BORDER),
            new Card(R.drawable.deck_diamond_red_solid_1, Color.RED, Number.ONE, Shape.DIAMOND, Type.SOLID),
            new Card(R.drawable.deck_diamond_red_solid_2, Color.RED, Number.TWO, Shape.DIAMOND, Type.SOLID),
            new Card(R.drawable.deck_diamond_red_solid_3, Color.RED, Number.THREE, Shape.DIAMOND, Type.SOLID),
            new Card(R.drawable.deck_diamond_red_striped_1, Color.RED, Number.ONE, Shape.DIAMOND, Type.STRIPED),
            new Card(R.drawable.deck_diamond_red_striped_2, Color.RED, Number.TWO, Shape.DIAMOND, Type.STRIPED),
            new Card(R.drawable.deck_diamond_red_striped_3, Color.RED, Number.THREE, Shape.DIAMOND, Type.STRIPED),
            new Card(R.drawable.deck_oval_blue_border_1, Color.BLUE, Number.ONE, Shape.OVAL, Type.BORDER),
            new Card(R.drawable.deck_oval_blue_border_2, Color.BLUE, Number.TWO, Shape.OVAL, Type.BORDER),
            new Card(R.drawable.deck_oval_blue_border_3, Color.BLUE, Number.THREE, Shape.OVAL, Type.BORDER),
            new Card(R.drawable.deck_oval_blue_solid_1, Color.BLUE, Number.ONE, Shape.OVAL, Type.SOLID),
            new Card(R.drawable.deck_oval_blue_solid_2, Color.BLUE, Number.TWO, Shape.OVAL, Type.SOLID),
            new Card(R.drawable.deck_oval_blue_solid_3, Color.BLUE, Number.THREE, Shape.OVAL, Type.SOLID),
            new Card(R.drawable.deck_oval_blue_striped_1, Color.BLUE, Number.ONE, Shape.OVAL, Type.STRIPED),
            new Card(R.drawable.deck_oval_blue_striped_2, Color.BLUE, Number.TWO, Shape.OVAL, Type.STRIPED),
            new Card(R.drawable.deck_oval_blue_striped_3, Color.BLUE, Number.THREE, Shape.OVAL, Type.STRIPED),
            new Card(R.drawable.deck_oval_green_border_1, Color.GREEN, Number.ONE, Shape.OVAL, Type.BORDER),
            new Card(R.drawable.deck_oval_green_border_2, Color.GREEN, Number.TWO, Shape.OVAL, Type.BORDER),
            new Card(R.drawable.deck_oval_green_border_3, Color.GREEN, Number.THREE, Shape.OVAL, Type.BORDER),
            new Card(R.drawable.deck_oval_green_solid_1, Color.GREEN, Number.ONE, Shape.OVAL, Type.SOLID),
            new Card(R.drawable.deck_oval_green_solid_2, Color.GREEN, Number.TWO, Shape.OVAL, Type.SOLID),
            new Card(R.drawable.deck_oval_green_solid_3, Color.GREEN, Number.THREE, Shape.OVAL, Type.SOLID),
            new Card(R.drawable.deck_oval_green_striped_1, Color.GREEN, Number.ONE, Shape.OVAL, Type.STRIPED),
            new Card(R.drawable.deck_oval_green_striped_2, Color.GREEN, Number.TWO, Shape.OVAL, Type.STRIPED),
            new Card(R.drawable.deck_oval_green_striped_3, Color.GREEN, Number.THREE, Shape.OVAL, Type.STRIPED),
            new Card(R.drawable.deck_oval_red_border_1, Color.RED, Number.ONE, Shape.OVAL, Type.BORDER),
            new Card(R.drawable.deck_oval_red_border_2, Color.RED, Number.TWO, Shape.OVAL, Type.BORDER),
            new Card(R.drawable.deck_oval_red_border_3, Color.RED, Number.THREE, Shape.OVAL, Type.BORDER),
            new Card(R.drawable.deck_oval_red_solid_1, Color.RED, Number.ONE, Shape.OVAL, Type.SOLID),
            new Card(R.drawable.deck_oval_red_solid_2, Color.RED, Number.TWO, Shape.OVAL, Type.SOLID),
            new Card(R.drawable.deck_oval_red_solid_3, Color.RED, Number.THREE, Shape.OVAL, Type.SOLID),
            new Card(R.drawable.deck_oval_red_striped_1, Color.RED, Number.ONE, Shape.OVAL, Type.STRIPED),
            new Card(R.drawable.deck_oval_red_striped_2, Color.RED, Number.TWO, Shape.OVAL, Type.STRIPED),
            new Card(R.drawable.deck_oval_red_striped_3, Color.RED, Number.THREE, Shape.OVAL, Type.STRIPED),
            new Card(R.drawable.deck_squiggle_blue_border_1, Color.BLUE, Number.ONE, Shape.SQUIGGLE, Type.BORDER),
            new Card(R.drawable.deck_squiggle_blue_border_2, Color.BLUE, Number.TWO, Shape.SQUIGGLE, Type.BORDER),
            new Card(R.drawable.deck_squiggle_blue_border_3, Color.BLUE, Number.THREE, Shape.SQUIGGLE, Type.BORDER),
            new Card(R.drawable.deck_squiggle_blue_solid_1, Color.BLUE, Number.ONE, Shape.SQUIGGLE, Type.SOLID),
            new Card(R.drawable.deck_squiggle_blue_solid_2, Color.BLUE, Number.TWO, Shape.SQUIGGLE, Type.SOLID),
            new Card(R.drawable.deck_squiggle_blue_solid_3, Color.BLUE, Number.THREE, Shape.SQUIGGLE, Type.SOLID),
            new Card(R.drawable.deck_squiggle_blue_striped_1, Color.BLUE, Number.ONE, Shape.SQUIGGLE, Type.STRIPED),
            new Card(R.drawable.deck_squiggle_blue_striped_2, Color.BLUE, Number.TWO, Shape.SQUIGGLE, Type.STRIPED),
            new Card(R.drawable.deck_squiggle_blue_striped_3, Color.BLUE, Number.THREE, Shape.SQUIGGLE, Type.STRIPED),
            new Card(R.drawable.deck_squiggle_green_border_1, Color.GREEN, Number.ONE, Shape.SQUIGGLE, Type.BORDER),
            new Card(R.drawable.deck_squiggle_green_border_2, Color.GREEN, Number.TWO, Shape.SQUIGGLE, Type.BORDER),
            new Card(R.drawable.deck_squiggle_green_border_3, Color.GREEN, Number.THREE, Shape.SQUIGGLE, Type.BORDER),
            new Card(R.drawable.deck_squiggle_green_solid_1, Color.GREEN, Number.ONE, Shape.SQUIGGLE, Type.SOLID),
            new Card(R.drawable.deck_squiggle_green_solid_2, Color.GREEN, Number.TWO, Shape.SQUIGGLE, Type.SOLID),
            new Card(R.drawable.deck_squiggle_green_solid_3, Color.GREEN, Number.THREE, Shape.SQUIGGLE, Type.SOLID),
            new Card(R.drawable.deck_squiggle_green_striped_1, Color.GREEN, Number.ONE, Shape.SQUIGGLE, Type.STRIPED),
            new Card(R.drawable.deck_squiggle_green_striped_2, Color.GREEN, Number.TWO, Shape.SQUIGGLE, Type.STRIPED),
            new Card(R.drawable.deck_squiggle_green_striped_3, Color.GREEN, Number.THREE, Shape.SQUIGGLE, Type.STRIPED),
            new Card(R.drawable.deck_squiggle_red_border_1, Color.RED, Number.ONE, Shape.SQUIGGLE, Type.BORDER),
            new Card(R.drawable.deck_squiggle_red_border_2, Color.RED, Number.TWO, Shape.SQUIGGLE, Type.BORDER),
            new Card(R.drawable.deck_squiggle_red_border_3, Color.RED, Number.THREE, Shape.SQUIGGLE, Type.BORDER),
            new Card(R.drawable.deck_squiggle_red_solid_1, Color.RED, Number.ONE, Shape.SQUIGGLE, Type.SOLID),
            new Card(R.drawable.deck_squiggle_red_solid_2, Color.RED, Number.TWO, Shape.SQUIGGLE, Type.SOLID),
            new Card(R.drawable.deck_squiggle_red_solid_3, Color.RED, Number.THREE, Shape.SQUIGGLE, Type.SOLID),
            new Card(R.drawable.deck_squiggle_red_striped_1, Color.RED, Number.ONE, Shape.SQUIGGLE, Type.STRIPED),
            new Card(R.drawable.deck_squiggle_red_striped_2, Color.RED, Number.TWO, Shape.SQUIGGLE, Type.STRIPED),
            new Card(R.drawable.deck_squiggle_red_striped_3, Color.RED, Number.THREE, Shape.SQUIGGLE, Type.STRIPED),
    };
    public static boolean isSet(Card c1, Card c2, Card c3) {
        return isSetTrait((Card card) -> card.color, c1, c2, c3)
                && isSetTrait((Card card) -> card.number, c1, c2, c3)
                && isSetTrait((Card card) -> card.shape, c1, c2, c3)
                && isSetTrait((Card card) -> card.type, c1, c2, c3);
    }

    private static boolean isSetTrait(Function<Card, Trait> fn, Card c1, Card c2, Card c3) {
        Set<Trait> traits = new HashSet<>();
        traits.add(fn.apply(c1));
        traits.add(fn.apply(c2));
        traits.add(fn.apply(c3));
        return traits.size() == 1 || traits.size() == 3;
    }

    public static Card getComplimentaryCard(Card c1, Card c2) {
        return Arrays
                .stream(Deck.DECK)
                .filter(c -> c.color == getComplimentaryTrait(c1.color, c2.color)
                        && c.number == getComplimentaryTrait(c1.number, c2.number)
                        && c.shape == getComplimentaryTrait(c1.shape, c2.shape)
                        && c.type == getComplimentaryTrait(c1.type, c2.type))
                .findFirst()
                .orElse(null);
    }

    private static Trait getComplimentaryTrait(Trait t1, Trait t2) {
        if (t1.getClass() != t2.getClass()) {
            return null;
        }
        if (t1 == t2) {
            return t1;
        }
        return Arrays
                .stream(Objects.requireNonNull(t1.getClass().getEnumConstants()))
                .filter(t -> t != t1 && t != t2)
                .findFirst()
                .orElse(null);
    }
}
