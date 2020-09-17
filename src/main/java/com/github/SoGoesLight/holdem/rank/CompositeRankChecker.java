package com.github.SoGoesLight.holdem.rank;

import com.github.SoGoesLight.holdem.domain.Combination;
import com.github.SoGoesLight.holdem.domain.SevenCards;

import java.util.List;
import java.util.Optional;

public class CompositeRankChecker implements RankChecker {

    private final List<RankChecker> checkers;

    public CompositeRankChecker(List<RankChecker> checkers) {
        this.checkers = checkers;
    }

    @Override
    public Optional<Combination> check(SevenCards sevenCards) {
        return checkers.stream()
                .map(c -> c.check(sevenCards))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

}
