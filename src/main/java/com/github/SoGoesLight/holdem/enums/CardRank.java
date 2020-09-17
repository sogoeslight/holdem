package com.github.SoGoesLight.holdem.enums;

import java.util.Arrays;
import java.util.Optional;

public enum CardRank {
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    TEN('T'),
    JACK('J'),
    QUEEN('Q'),
    KING('K'),
    ACE('A');

    public final char symbol;

    CardRank(char symbol) {
        this.symbol = symbol;
    }

    static public Optional<CardRank> fromSymbol(char symbol) {
        return Arrays.stream(CardRank.values())
                .filter(rank -> rank.symbol == symbol)
                .findFirst();
    }
}
