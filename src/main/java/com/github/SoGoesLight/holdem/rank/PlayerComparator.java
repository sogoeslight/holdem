package com.github.SoGoesLight.holdem.rank;

import com.github.SoGoesLight.holdem.domain.Player;
import com.github.SoGoesLight.holdem.enums.CombinationRank;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerComparator {

    public List<Player> compared(List<Player> players, CombinationRank rank) {

        // We will check different kickers for different combinations to optimize app a bit
        switch (rank) {
            case HIGH_CARD:
            case FLUSH:
                return players.stream().sorted(Comparator.comparing(Player::getKickerOne)
                        .thenComparing(Player::getKickerTwo).thenComparing(Player::getKickerThree)
                        .thenComparing(Player::getKickerFour).thenComparing(Player::getKickerFive))
                        .collect(Collectors.toList());
            case ONE_PAIR:
                return players.stream().sorted(Comparator.comparing(Player::getKickerOne)
                        .thenComparing(Player::getKickerThree)
                        .thenComparing(Player::getKickerFour).thenComparing(Player::getKickerFive))
                        .collect(Collectors.toList());
            case TWO_PAIRS:
                return players.stream().sorted(Comparator.comparing(Player::getKickerOne)
                        .thenComparing(Player::getKickerThree)
                        .thenComparing(Player::getKickerFive))
                        .collect(Collectors.toList());
            case THREE_OF_A_KIND:
                return players.stream().sorted(Comparator.comparing(Player::getKickerOne)
                        .thenComparing(Player::getKickerFour).thenComparing(Player::getKickerFive))
                        .collect(Collectors.toList());
            case STRAIGHT:
            case STRAIGHT_FLUSH:
                return players.stream().sorted(Comparator.comparing(Player::getKickerOne))
                        .collect(Collectors.toList());
            case FULL_HOUSE:
                return players.stream().sorted(Comparator.comparing(Player::getKickerOne)
                        .thenComparing(Player::getKickerFour))
                        .collect(Collectors.toList());
            case FOUR_OF_A_KIND:
                return players.stream().sorted(Comparator.comparing(Player::getKickerOne)
                        .thenComparing(Player::getKickerFive))
                        .collect(Collectors.toList());
            case ROYAL_FLUSH:
                return new ArrayList<>(players);
        }

        return players.stream().sorted(Comparator.comparing(Player::getKickerOne)
                .thenComparing(Player::getKickerTwo).thenComparing(Player::getKickerThree)
                .thenComparing(Player::getKickerFour).thenComparing(Player::getKickerFive))
                .collect(Collectors.toList());
    }

}
