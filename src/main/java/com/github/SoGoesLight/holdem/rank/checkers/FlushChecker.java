package com.github.SoGoesLight.holdem.rank.checkers;

import com.github.SoGoesLight.holdem.domain.Card;
import com.github.SoGoesLight.holdem.domain.Combination;
import com.github.SoGoesLight.holdem.domain.SevenCards;
import com.github.SoGoesLight.holdem.enums.CombinationRank;
import com.github.SoGoesLight.holdem.rank.RankChecker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class FlushChecker implements RankChecker {

    @Override
    public Optional<Combination> check(SevenCards sevenCards) {
        List<Card> flush = new ArrayList<>();

        sevenCards.getCards().sort(Comparator.comparing(Card::getRank).reversed());

        for (Card card1 : sevenCards.getCards()) {
            for (Card card2 : sevenCards.getCards()) {
                if (!card1.getRank().equals(card2.getRank()) && card1.getSuit().equals(card2.getSuit())) {
                    if (!flush.contains(card1)) {
                        flush.add(card1);
                    }
                    if (!flush.contains(card2)) {
                        flush.add(card2);
                    }
                    if (flush.size() == 5) {
                        Optional<Combination> result = Optional.of(new Combination(flush, CombinationRank.FLUSH));
                        result.get().getCards().sort(Comparator.comparing(Card::getRank).reversed());
                        return result;
                    }
                }
            }
            flush.clear();
        }

        return Optional.empty();
    }

}
