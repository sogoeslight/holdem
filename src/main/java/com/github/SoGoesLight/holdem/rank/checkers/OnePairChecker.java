package com.github.SoGoesLight.holdem.rank.checkers;

import com.github.SoGoesLight.holdem.domain.Card;
import com.github.SoGoesLight.holdem.domain.Combination;
import com.github.SoGoesLight.holdem.domain.SevenCards;
import com.github.SoGoesLight.holdem.enums.CombinationRank;
import com.github.SoGoesLight.holdem.rank.RankChecker;

import java.util.List;
import java.util.Optional;

public class OnePairChecker implements RankChecker {

    @Override
    public Optional<Combination> check(SevenCards sevenCards) {
        Optional<List<Card>> onePair = Pair.check(sevenCards, 2);

        return onePair.map(cards -> new Combination(cards, CombinationRank.ONE_PAIR));
    }

}
