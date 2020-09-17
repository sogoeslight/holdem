package com.github.SoGoesLight.holdem.rank.checkers;

import com.github.SoGoesLight.holdem.domain.Card;
import com.github.SoGoesLight.holdem.domain.SevenCards;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Pair {

    public static Optional<List<Card>> check(SevenCards sevenCards, Integer pairSize) {
        List<Card> checkedPair = new ArrayList<>();
        List<Card> allCards = sevenCards.getCards();

        for (int i = 0; i < allCards.size(); i++) {
            checkedPair.add(allCards.get(i));

            for (int j = i + 1; j < allCards.size(); j++) {
                if (allCards.get(i).getRank().equals(allCards.get(j).getRank())) {
                    checkedPair.add(allCards.get(j));
                }
            }

            if (checkedPair.size() == pairSize) {
                return Optional.of(checkedPair);
            }
            checkedPair.clear();
        }

        return Optional.empty();
    }

}
