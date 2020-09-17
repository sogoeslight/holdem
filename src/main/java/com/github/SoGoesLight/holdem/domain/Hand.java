package com.github.SoGoesLight.holdem.domain;

import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return String.format("%s%s", cards.get(0), cards.get(1));
    }

}
