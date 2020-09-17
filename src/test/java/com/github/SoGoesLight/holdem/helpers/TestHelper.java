package com.github.SoGoesLight.holdem.helpers;

import com.github.SoGoesLight.holdem.domain.Board;
import com.github.SoGoesLight.holdem.domain.Card;
import com.github.SoGoesLight.holdem.domain.Hand;
import com.github.SoGoesLight.holdem.domain.SevenCards;
import com.github.SoGoesLight.holdem.services.CardParser;

import java.util.List;

public class TestHelper {

    public static List<Card> createCardList(String str) {
        return CardParser.parseCards(str);
    }

    public static SevenCards createSevenCards(String board, String hand) {
        return new SevenCards(new Board(createCardList(board)), new Hand(createCardList(hand)));
    }

}
