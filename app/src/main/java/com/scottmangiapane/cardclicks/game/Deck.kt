package com.scottmangiapane.cardclicks.game

import com.scottmangiapane.cardclicks.R

object Deck {
    @JvmField
    val DECK: Array<Card> = arrayOf(
        Card(R.drawable.deck_diamond_blue_border_1, Color.BLUE, Number.ONE, Shape.DIAMOND, Type.BORDER),
        Card(R.drawable.deck_diamond_blue_border_2, Color.BLUE, Number.TWO, Shape.DIAMOND, Type.BORDER),
        Card(R.drawable.deck_diamond_blue_border_3, Color.BLUE, Number.THREE, Shape.DIAMOND, Type.BORDER),
        Card(R.drawable.deck_diamond_blue_solid_1, Color.BLUE, Number.ONE, Shape.DIAMOND, Type.SOLID),
        Card(R.drawable.deck_diamond_blue_solid_2, Color.BLUE, Number.TWO, Shape.DIAMOND, Type.SOLID),
        Card(R.drawable.deck_diamond_blue_solid_3, Color.BLUE, Number.THREE, Shape.DIAMOND, Type.SOLID),
        Card(R.drawable.deck_diamond_blue_striped_1, Color.BLUE, Number.ONE, Shape.DIAMOND, Type.STRIPED),
        Card(R.drawable.deck_diamond_blue_striped_2, Color.BLUE, Number.TWO, Shape.DIAMOND, Type.STRIPED),
        Card(R.drawable.deck_diamond_blue_striped_3, Color.BLUE, Number.THREE, Shape.DIAMOND, Type.STRIPED),
        Card(R.drawable.deck_diamond_green_border_1, Color.GREEN, Number.ONE, Shape.DIAMOND, Type.BORDER),
        Card(R.drawable.deck_diamond_green_border_2, Color.GREEN, Number.TWO, Shape.DIAMOND, Type.BORDER),
        Card(R.drawable.deck_diamond_green_border_3, Color.GREEN, Number.THREE, Shape.DIAMOND, Type.BORDER),
        Card(R.drawable.deck_diamond_green_solid_1, Color.GREEN, Number.ONE, Shape.DIAMOND, Type.SOLID),
        Card(R.drawable.deck_diamond_green_solid_2, Color.GREEN, Number.TWO, Shape.DIAMOND, Type.SOLID),
        Card(R.drawable.deck_diamond_green_solid_3, Color.GREEN, Number.THREE, Shape.DIAMOND, Type.SOLID),
        Card(R.drawable.deck_diamond_green_striped_1, Color.GREEN, Number.ONE, Shape.DIAMOND, Type.STRIPED),
        Card(R.drawable.deck_diamond_green_striped_2, Color.GREEN, Number.TWO, Shape.DIAMOND, Type.STRIPED),
        Card(R.drawable.deck_diamond_green_striped_3, Color.GREEN, Number.THREE, Shape.DIAMOND, Type.STRIPED),
        Card(R.drawable.deck_diamond_red_border_1, Color.RED, Number.ONE, Shape.DIAMOND, Type.BORDER),
        Card(R.drawable.deck_diamond_red_border_2, Color.RED, Number.TWO, Shape.DIAMOND, Type.BORDER),
        Card(R.drawable.deck_diamond_red_border_3, Color.RED, Number.THREE, Shape.DIAMOND, Type.BORDER),
        Card(R.drawable.deck_diamond_red_solid_1, Color.RED, Number.ONE, Shape.DIAMOND, Type.SOLID),
        Card(R.drawable.deck_diamond_red_solid_2, Color.RED, Number.TWO, Shape.DIAMOND, Type.SOLID),
        Card(R.drawable.deck_diamond_red_solid_3, Color.RED, Number.THREE, Shape.DIAMOND, Type.SOLID),
        Card(R.drawable.deck_diamond_red_striped_1, Color.RED, Number.ONE, Shape.DIAMOND, Type.STRIPED),
        Card(R.drawable.deck_diamond_red_striped_2, Color.RED, Number.TWO, Shape.DIAMOND, Type.STRIPED),
        Card(R.drawable.deck_diamond_red_striped_3, Color.RED, Number.THREE, Shape.DIAMOND, Type.STRIPED),
        Card(R.drawable.deck_oval_blue_border_1, Color.BLUE, Number.ONE, Shape.OVAL, Type.BORDER),
        Card(R.drawable.deck_oval_blue_border_2, Color.BLUE, Number.TWO, Shape.OVAL, Type.BORDER),
        Card(R.drawable.deck_oval_blue_border_3, Color.BLUE, Number.THREE, Shape.OVAL, Type.BORDER),
        Card(R.drawable.deck_oval_blue_solid_1, Color.BLUE, Number.ONE, Shape.OVAL, Type.SOLID),
        Card(R.drawable.deck_oval_blue_solid_2, Color.BLUE, Number.TWO, Shape.OVAL, Type.SOLID),
        Card(R.drawable.deck_oval_blue_solid_3, Color.BLUE, Number.THREE, Shape.OVAL, Type.SOLID),
        Card(R.drawable.deck_oval_blue_striped_1, Color.BLUE, Number.ONE, Shape.OVAL, Type.STRIPED),
        Card(R.drawable.deck_oval_blue_striped_2, Color.BLUE, Number.TWO, Shape.OVAL, Type.STRIPED),
        Card(R.drawable.deck_oval_blue_striped_3, Color.BLUE, Number.THREE, Shape.OVAL, Type.STRIPED),
        Card(R.drawable.deck_oval_green_border_1, Color.GREEN, Number.ONE, Shape.OVAL, Type.BORDER),
        Card(R.drawable.deck_oval_green_border_2, Color.GREEN, Number.TWO, Shape.OVAL, Type.BORDER),
        Card(R.drawable.deck_oval_green_border_3, Color.GREEN, Number.THREE, Shape.OVAL, Type.BORDER),
        Card(R.drawable.deck_oval_green_solid_1, Color.GREEN, Number.ONE, Shape.OVAL, Type.SOLID),
        Card(R.drawable.deck_oval_green_solid_2, Color.GREEN, Number.TWO, Shape.OVAL, Type.SOLID),
        Card(R.drawable.deck_oval_green_solid_3, Color.GREEN, Number.THREE, Shape.OVAL, Type.SOLID),
        Card(R.drawable.deck_oval_green_striped_1, Color.GREEN, Number.ONE, Shape.OVAL, Type.STRIPED),
        Card(R.drawable.deck_oval_green_striped_2, Color.GREEN, Number.TWO, Shape.OVAL, Type.STRIPED),
        Card(R.drawable.deck_oval_green_striped_3, Color.GREEN, Number.THREE, Shape.OVAL, Type.STRIPED),
        Card(R.drawable.deck_oval_red_border_1, Color.RED, Number.ONE, Shape.OVAL, Type.BORDER),
        Card(R.drawable.deck_oval_red_border_2, Color.RED, Number.TWO, Shape.OVAL, Type.BORDER),
        Card(R.drawable.deck_oval_red_border_3, Color.RED, Number.THREE, Shape.OVAL, Type.BORDER),
        Card(R.drawable.deck_oval_red_solid_1, Color.RED, Number.ONE, Shape.OVAL, Type.SOLID),
        Card(R.drawable.deck_oval_red_solid_2, Color.RED, Number.TWO, Shape.OVAL, Type.SOLID),
        Card(R.drawable.deck_oval_red_solid_3, Color.RED, Number.THREE, Shape.OVAL, Type.SOLID),
        Card(R.drawable.deck_oval_red_striped_1, Color.RED, Number.ONE, Shape.OVAL, Type.STRIPED),
        Card(R.drawable.deck_oval_red_striped_2, Color.RED, Number.TWO, Shape.OVAL, Type.STRIPED),
        Card(R.drawable.deck_oval_red_striped_3, Color.RED, Number.THREE, Shape.OVAL, Type.STRIPED),
        Card(R.drawable.deck_squiggle_blue_border_1, Color.BLUE, Number.ONE, Shape.SQUIGGLE, Type.BORDER),
        Card(R.drawable.deck_squiggle_blue_border_2, Color.BLUE, Number.TWO, Shape.SQUIGGLE, Type.BORDER),
        Card(R.drawable.deck_squiggle_blue_border_3, Color.BLUE, Number.THREE, Shape.SQUIGGLE, Type.BORDER),
        Card(R.drawable.deck_squiggle_blue_solid_1, Color.BLUE, Number.ONE, Shape.SQUIGGLE, Type.SOLID),
        Card(R.drawable.deck_squiggle_blue_solid_2, Color.BLUE, Number.TWO, Shape.SQUIGGLE, Type.SOLID),
        Card(R.drawable.deck_squiggle_blue_solid_3, Color.BLUE, Number.THREE, Shape.SQUIGGLE, Type.SOLID),
        Card(R.drawable.deck_squiggle_blue_striped_1, Color.BLUE, Number.ONE, Shape.SQUIGGLE, Type.STRIPED),
        Card(R.drawable.deck_squiggle_blue_striped_2, Color.BLUE, Number.TWO, Shape.SQUIGGLE, Type.STRIPED),
        Card(R.drawable.deck_squiggle_blue_striped_3, Color.BLUE, Number.THREE, Shape.SQUIGGLE, Type.STRIPED),
        Card(R.drawable.deck_squiggle_green_border_1, Color.GREEN, Number.ONE, Shape.SQUIGGLE, Type.BORDER),
        Card(R.drawable.deck_squiggle_green_border_2, Color.GREEN, Number.TWO, Shape.SQUIGGLE, Type.BORDER),
        Card(R.drawable.deck_squiggle_green_border_3, Color.GREEN, Number.THREE, Shape.SQUIGGLE, Type.BORDER),
        Card(R.drawable.deck_squiggle_green_solid_1, Color.GREEN, Number.ONE, Shape.SQUIGGLE, Type.SOLID),
        Card(R.drawable.deck_squiggle_green_solid_2, Color.GREEN, Number.TWO, Shape.SQUIGGLE, Type.SOLID),
        Card(R.drawable.deck_squiggle_green_solid_3, Color.GREEN, Number.THREE, Shape.SQUIGGLE, Type.SOLID),
        Card(R.drawable.deck_squiggle_green_striped_1, Color.GREEN, Number.ONE, Shape.SQUIGGLE, Type.STRIPED),
        Card(R.drawable.deck_squiggle_green_striped_2, Color.GREEN, Number.TWO, Shape.SQUIGGLE, Type.STRIPED),
        Card(R.drawable.deck_squiggle_green_striped_3, Color.GREEN, Number.THREE, Shape.SQUIGGLE, Type.STRIPED),
        Card(R.drawable.deck_squiggle_red_border_1, Color.RED, Number.ONE, Shape.SQUIGGLE, Type.BORDER),
        Card(R.drawable.deck_squiggle_red_border_2, Color.RED, Number.TWO, Shape.SQUIGGLE, Type.BORDER),
        Card(R.drawable.deck_squiggle_red_border_3, Color.RED, Number.THREE, Shape.SQUIGGLE, Type.BORDER),
        Card(R.drawable.deck_squiggle_red_solid_1, Color.RED, Number.ONE, Shape.SQUIGGLE, Type.SOLID),
        Card(R.drawable.deck_squiggle_red_solid_2, Color.RED, Number.TWO, Shape.SQUIGGLE, Type.SOLID),
        Card(R.drawable.deck_squiggle_red_solid_3, Color.RED, Number.THREE, Shape.SQUIGGLE, Type.SOLID),
        Card(R.drawable.deck_squiggle_red_striped_1, Color.RED, Number.ONE, Shape.SQUIGGLE, Type.STRIPED),
        Card(R.drawable.deck_squiggle_red_striped_2, Color.RED, Number.TWO, Shape.SQUIGGLE, Type.STRIPED),
        Card(R.drawable.deck_squiggle_red_striped_3, Color.RED, Number.THREE, Shape.SQUIGGLE, Type.STRIPED),
    )

    @JvmStatic
    fun isSet(c1: Card, c2: Card, c3: Card): Boolean {
        return isSetTrait(Card::color, c1, c2, c3)
                && isSetTrait(Card::number, c1, c2, c3)
                && isSetTrait(Card::shape, c1, c2, c3)
                && isSetTrait(Card::type, c1, c2, c3)
    }

    private fun isSetTrait(fn: (Card) -> Trait, c1: Card, c2: Card, c3: Card): Boolean {
        val traits = setOf(fn(c1), fn(c2), fn(c3))
        return traits.size == 1 || traits.size == 3
    }

    @JvmStatic
    fun getComplimentaryCard(c1: Card, c2: Card): Card {
        return DECK.find { c ->
            c.color == getComplimentaryTrait(c1.color, c2.color) &&
                    c.number == getComplimentaryTrait(c1.number, c2.number) &&
                    c.shape == getComplimentaryTrait(c1.shape, c2.shape) &&
                    c.type == getComplimentaryTrait(c1.type, c2.type)
        }!!
    }

    private fun getComplimentaryTrait(t1: Trait, t2: Trait): Trait {
        if (t1 == t2) {
            return t1
        }
        return t1.javaClass.enumConstants!!.find { it != t1 && it != t2 } as Trait
    }
}
