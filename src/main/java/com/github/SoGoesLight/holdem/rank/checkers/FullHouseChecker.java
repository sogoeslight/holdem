package com.github.SoGoesLight.holdem.rank.checkers;

import com.github.SoGoesLight.holdem.domain.Card;
import com.github.SoGoesLight.holdem.domain.Combination;
import com.github.SoGoesLight.holdem.domain.SevenCards;
import com.github.SoGoesLight.holdem.enums.CombinationRank;
import com.github.SoGoesLight.holdem.rank.RankChecker;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class FullHouseChecker implements RankChecker {

    @Override
    public Optional<Combination> check(SevenCards sevenCards) {
        Optional<List<Card>> fullHouse = Pair.check(sevenCards, 3);

        if (fullHouse.isPresent()) {
            SevenCards temp = new SevenCards(sevenCards.getBoard(), sevenCards.getHand());
            temp.getCards().removeAll(fullHouse.get());
            temp.getCards().sort(Comparator.comparing(Card::getRank).reversed());
            Optional<List<Card>> pair = Pair.check(temp, 2);
            if (pair.isPresent()) {
                fullHouse.get().addAll(pair.get());
                return Optional.of(new Combination(fullHouse.get(), CombinationRank.FULL_HOUSE));
            }
        }

        return Optional.empty();
    }

}
