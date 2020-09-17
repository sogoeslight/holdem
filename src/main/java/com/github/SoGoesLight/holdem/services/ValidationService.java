package com.github.SoGoesLight.holdem.services;

public class ValidationService {

    public void validateInput(String input) {
        String[] tokens = input.split(" ");

        // Check amount of players
        if (tokens.length < 2) {
            throw new IllegalArgumentException("Input error! Input must contain at least 2 token (1 board and 1 player).");
        }

        if (tokens[0].length() == 0)
            throw new IllegalArgumentException("Input error! No extra spacings allowed.");

        // Check size of board
        if (tokens[0].length() < 10)
            throw new IllegalArgumentException("Input error! Board token must contain 10 symbols. Current length: " + tokens.length);

        // Check board content
        for (int j = 1; j < 10; j += 2) {
            if (tokens[0].charAt(j) != tokens[0].toLowerCase().charAt(j)) {
                throw new IllegalArgumentException("Input error! Cards suit must be lowercase.");
            }
            if (tokens[0].charAt(j - 1) != tokens[0].toUpperCase().charAt(j - 1)) {
                throw new IllegalArgumentException("Input error! Cards rank must be uppercase.");
            }
            if ((tokens[0].charAt(j) != 'd') && (tokens[0].charAt(j) != 'c')
                    && (tokens[0].charAt(j) != 'h') && (tokens[0].charAt(j) != 's')) {
                throw new IllegalArgumentException("Input error! Only Diamonds, Clubs, Hearts and Spades suits are allowed.");
            }
        }

        // Check hands
        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].length() == 0) {
                throw new IllegalArgumentException("Input error! No extra spacings allowed.");
            } else {
                for (int j = 0; j < tokens[i].length(); j++) {
                    if (tokens[i].charAt(j) == ' ' || tokens[i].charAt(j) == '\t'
                            || tokens[i].charAt(j) == '\r' || tokens[i].charAt(j) == '\n') {
                        throw new IllegalArgumentException("Input error! No extra spacings and line breakers allowed.");
                    }
                }
            }
            if (tokens[i].length() != 4)
                throw new IllegalArgumentException("Input error! Card token must contain 4 symbols. Current length: " + tokens.length);

            if (tokens[i].charAt(1) != tokens[i].toLowerCase().charAt(1)
                    || tokens[i].charAt(3) != tokens[i].toLowerCase().charAt(3)) {
                throw new IllegalArgumentException("Input error! Cards suit must be lowercase.");
            }
            if (tokens[i].charAt(0) != tokens[i].toUpperCase().charAt(0)
                    || tokens[i].charAt(2) != tokens[i].toUpperCase().charAt(2)) {
                throw new IllegalArgumentException("Input error! Cards rank must be uppercase.");
            }
            if ((tokens[i].charAt(1) != 'd') && (tokens[i].charAt(1) != 'c')
                    && (tokens[i].charAt(1) != 'h') && (tokens[i].charAt(1) != 's')) {
                throw new IllegalArgumentException("Input error! Only Diamonds, Clubs, Hearts and Spades suits are allowed.");
            }
            if ((tokens[i].charAt(3) != 'd') && (tokens[i].charAt(3) != 'c')
                    && (tokens[i].charAt(3) != 'h') && (tokens[i].charAt(3) != 's')) {
                throw new IllegalArgumentException("Input error! Only Diamonds, Clubs, Hearts and Spades suits are allowed.");
            }
        }

        // Check for same cards
        StringBuilder card = new StringBuilder();
        StringBuilder nextCard = new StringBuilder();
        for (String token : tokens) {
            for (int j = 0; j < token.length(); j += 2) {
                card.append(token.charAt(j));
                card.append(token.charAt(j + 1));
                for (String s : tokens) {
                    for (int l = j + 2; l < s.length(); l += 2) {
                        nextCard.append(s.charAt(l));
                        nextCard.append(s.charAt(l + 1));
                        if (card.toString().equals(nextCard.toString())) {
                            throw new IllegalArgumentException("Input error! No repeated cards are allowed. '" + nextCard + "' repeats.");
                        }
                        nextCard = new StringBuilder();
                    }
                }
                card = new StringBuilder();
            }
        }
    }

}
