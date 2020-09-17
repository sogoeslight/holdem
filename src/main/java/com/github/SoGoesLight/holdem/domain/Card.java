package com.github.SoGoesLight.holdem.domain;

import com.github.SoGoesLight.holdem.enums.CardRank;
import com.github.SoGoesLight.holdem.enums.CardSuit;

public class Card {

    private final CardSuit cardSuit;
    private final CardRank cardRank;

    public Card(CardSuit cardSuit, CardRank cardRank) {
        this.cardSuit = cardSuit;
        this.cardRank = cardRank;
    }

    public CardSuit getSuit() {
        return cardSuit;
    }

    public CardRank getRank() {
        return cardRank;
    }

    public Integer getRankToInt() {
        return cardRank.ordinal();
    }

    @Override
    public String toString() {
        return String.format("%c%c", cardRank.symbol, cardSuit.symbol);
    }

}
