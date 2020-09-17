package com.github.SoGoesLight.holdem.domain;

import com.github.SoGoesLight.holdem.enums.CombinationRank;

import java.util.ArrayList;
import java.util.List;

public class Combination {

    private List<Card> combination = new ArrayList<>();
    private CombinationRank rank;

    public Combination() {
    }

    public Combination(List<Card> cards, CombinationRank rank) {
        this.combination = cards;
        this.rank = rank;
    }

    public List<Card> getCards() {
        return combination;
    }

    public CombinationRank getRank() {
        return rank;
    }

}
