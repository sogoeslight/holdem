package com.github.SoGoesLight.holdem.rank.checkers;

import com.github.SoGoesLight.holdem.domain.Card;
import com.github.SoGoesLight.holdem.domain.Combination;
import com.github.SoGoesLight.holdem.domain.SevenCards;
import com.github.SoGoesLight.holdem.enums.CombinationRank;
import com.github.SoGoesLight.holdem.rank.RankChecker;

import java.util.List;
import java.util.Optional;

public class ThreeOfAKindChecker implements RankChecker {

    @Override
    public Optional<Combination> check(SevenCards sevenCards) {
        Optional<List<Card>> threeOfAKind = Pair.check(sevenCards, 3);

        return threeOfAKind.map(cards -> new Combination(cards, CombinationRank.THREE_OF_A_KIND));
    }

}
