package com.github.SoGoesLight.holdem.domain;

import com.github.SoGoesLight.holdem.enums.CombinationRank;

import static java.util.stream.Collectors.joining;

public class Player {

    private final Hand hand;
    private final Combination combination;
    private final CombinationRank rank;
    private final Kicker kicker;

    public Player(Hand hand, Combination combination, CombinationRank rank, Kicker kicker) {
        this.hand = hand;
        this.combination = combination;
        this.rank = rank;
        this.kicker = kicker;
    }

    public Combination getCombination() {
        return combination;
    }

    //region Kicker getters
    public int getKickerOne() {
        return kicker.getKickersList().get(0);
    }

    public int getKickerTwo() {
        return kicker.getKickersList().get(1);
    }

    public int getKickerThree() {
        return kicker.getKickersList().get(2);
    }

    public int getKickerFour() {
        return kicker.getKickersList().get(3);
    }

    public int getKickerFive() {
        return kicker.getKickersList().get(4);
    }
//endregion

    public Kicker getKicker() {
        return kicker;
    }

    public CombinationRank getRank() {
        return rank;
    }

    public String getHandString() {
        return hand.toString();
    }

    @Override
    public String toString() {
        return combination.getCards().stream()
                .map(Card::toString)
                .collect(joining());
    }

}
