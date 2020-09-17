package com.github.SoGoesLight.holdem.rank.checkers;

import com.github.SoGoesLight.holdem.domain.Card;
import com.github.SoGoesLight.holdem.domain.Combination;
import com.github.SoGoesLight.holdem.domain.SevenCards;
import com.github.SoGoesLight.holdem.enums.CardRank;
import com.github.SoGoesLight.holdem.enums.CombinationRank;
import com.github.SoGoesLight.holdem.rank.RankChecker;

import java.util.List;
import java.util.Optional;

public class RoyalFlushChecker implements RankChecker {

    @Override
    public Optional<Combination> check(SevenCards sevenCards) {
        Optional<List<Card>> royalFlush = sevenCards.getCardSequence(true);

        if (royalFlush.isPresent()
                && royalFlush.get().stream().anyMatch(c -> c.getRank().equals(CardRank.ACE))
                && royalFlush.get().stream().anyMatch(c -> c.getRank().equals(CardRank.TEN))) {
            return Optional.of(new Combination(royalFlush.get(), CombinationRank.ROYAL_FLUSH));
        }

        return Optional.empty();
    }

}
