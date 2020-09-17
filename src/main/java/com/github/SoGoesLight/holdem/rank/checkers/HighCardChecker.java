package com.github.SoGoesLight.holdem.rank.checkers;

import com.github.SoGoesLight.holdem.domain.Card;
import com.github.SoGoesLight.holdem.domain.Combination;
import com.github.SoGoesLight.holdem.domain.SevenCards;
import com.github.SoGoesLight.holdem.enums.CombinationRank;
import com.github.SoGoesLight.holdem.rank.RankChecker;

import java.util.List;
import java.util.Optional;

public class HighCardChecker implements RankChecker {

    @Override
    public Optional<Combination> check(SevenCards sevenCards) {
        Card highCard = sevenCards.getCards().get(0);

        for (int i = 1; i < sevenCards.getCards().size(); i++) {
            Card card = sevenCards.getCards().get(i);
            if (card.getRankToInt() > highCard.getRankToInt()) {
                highCard = card;
            }
        }

        return Optional.of(new Combination(List.of(highCard), CombinationRank.HIGH_CARD));
    }

}
