package com.github.SoGoesLight.holdem.rank.checkers;

import com.github.SoGoesLight.holdem.domain.Card;
import com.github.SoGoesLight.holdem.domain.Combination;
import com.github.SoGoesLight.holdem.domain.SevenCards;
import com.github.SoGoesLight.holdem.enums.CombinationRank;
import com.github.SoGoesLight.holdem.rank.RankChecker;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TwoPairsChecker implements RankChecker {

    public Optional<Combination> check(SevenCards sevenCards) {
        Optional<List<Card>> twoPairs = Pair.check(sevenCards, 2);

        if (twoPairs.isPresent()) {
            SevenCards temp = new SevenCards(sevenCards.getBoard(), sevenCards.getHand());
            temp.getCards().removeAll(twoPairs.get());
            temp.getCards().sort(Comparator.comparing(Card::getRank).reversed());
            Optional<List<Card>> secondPair = Pair.check(temp, 2);
            if (secondPair.isPresent()) {
                twoPairs.get().addAll(secondPair.get());
                Optional<Combination> result = Optional.of(new Combination(twoPairs.get(), CombinationRank.TWO_PAIRS));
                result.get().getCards().sort(Comparator.comparing(Card::getRank).reversed());
                return result;
            }
        }

        return Optional.empty();
    }
}
