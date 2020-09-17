package com.github.SoGoesLight.holdem.domain;

import java.util.List;

public class Game {

    private final Board board;
    private final List<Hand> hands;

    public Game(Board board, List<Hand> hands) {
        this.board = board;
        this.hands = hands;
    }

    public Board getBoard() {
        return board;
    }

    public List<Hand> getHands() {
        return hands;
    }

}
