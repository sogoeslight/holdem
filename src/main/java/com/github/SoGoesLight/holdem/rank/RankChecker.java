package com.github.SoGoesLight.holdem.rank;

import com.github.SoGoesLight.holdem.domain.Combination;
import com.github.SoGoesLight.holdem.domain.SevenCards;

import java.util.Optional;

public interface RankChecker {

    Optional<Combination> check(SevenCards sevenCards);

}
