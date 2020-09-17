package com.github.SoGoesLight.holdem.services;

import com.github.SoGoesLight.holdem.domain.Card;
import com.github.SoGoesLight.holdem.enums.CardRank;
import com.github.SoGoesLight.holdem.enums.CardSuit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardParser {

    public static List<Card> parseCards(String cards) {
        List<Card> parsedCards = new ArrayList<>();
        for (int i = 0; i < cards.length(); i += 2) {
            Optional<CardRank> rank = CardRank.fromSymbol(cards.charAt(i));
            Optional<CardSuit> suit = CardSuit.fromSymbol(cards.charAt(i + 1));
            if (rank.isPresent() && suit.isPresent()) {
                parsedCards.add(new Card(suit.get(), rank.get()));
            }
        }

        return parsedCards;
    }

}
