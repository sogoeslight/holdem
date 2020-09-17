package com.github.SoGoesLight.holdem.enums;

import java.util.Arrays;
import java.util.Optional;

public enum CardSuit {
    DIAMONDS('d'),
    CLUBS('c'),
    HEARTS('h'),
    SPADES('s');

    public final char symbol;

    CardSuit(char symbol) {
        this.symbol = symbol;
    }

    static public Optional<CardSuit> fromSymbol(char symbol) {
        return Arrays.stream(CardSuit.values())
                .filter(rank -> rank.symbol == symbol)
                .findFirst();
    }
}
