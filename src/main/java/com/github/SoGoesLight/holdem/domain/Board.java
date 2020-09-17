package com.github.SoGoesLight.holdem.domain;

import java.util.List;

public class Board {

    private final List<Card> cards;

    public Board(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

}
