package com.github.SoGoesLight.holdem.services;

import com.github.SoGoesLight.holdem.domain.Board;
import com.github.SoGoesLight.holdem.domain.Game;
import com.github.SoGoesLight.holdem.domain.Hand;

import java.util.ArrayList;
import java.util.List;

public class GameParser {

    private final ValidationService validator = new ValidationService();

    public Game parse(String input) {
        validator.validateInput(input);
        String[] tokens = input.split(" ");

        Board board = new Board(CardParser.parseCards(tokens[0]));
        List<Hand> hands = new ArrayList<>();

        for (int i = 1; i < tokens.length; i++) {
            hands.add(new Hand(CardParser.parseCards(tokens[i])));
        }

        return new Game(board, hands);
    }

}
