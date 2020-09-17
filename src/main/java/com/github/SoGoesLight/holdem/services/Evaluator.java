package com.github.SoGoesLight.holdem.services;

import com.github.SoGoesLight.holdem.domain.*;
import com.github.SoGoesLight.holdem.rank.RankChecker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Evaluator {

    private final RankChecker rankChecker;

    public Evaluator(RankChecker rankChecker) {
        this.rankChecker = rankChecker;
    }

    public GameResult process(Game game) {
        List<Player> players;

        players = evaluatePlayers(game.getBoard(), game.getHands()).stream()
                .sorted(Comparator.comparing(Player::getRank).thenComparing(Player::getHandString))
                .collect(Collectors.toList());

        return new GameResult(players);
    }

    private List<Player> evaluatePlayers(Board board, List<Hand> hands) {
        List<Player> result = new ArrayList<>();

        for (Hand hand : hands) {
            SevenCards sevenCards = new SevenCards(board, hand);
            sevenCards.getCards().sort(Comparator.comparing(Card::getRank).reversed());

            Optional<Combination> combination = rankChecker.check(sevenCards);
            if (combination.isPresent()) {
                Kicker kicker = createKickers(sevenCards, combination.get(), hand);
                result.add(new Player(hand, combination.get(), combination.get().getRank(), kicker));
            }
        }

        return result;
    }

    public Kicker createKickers(SevenCards sevenCards, Combination combination, Hand hand) {
        List<Integer> kickers = new ArrayList<>();

        List<Card> temp = new ArrayList<>(sevenCards.getCards());
        temp.removeAll(combination.getCards());

        // fill kickers with combination card ranks first
        for (int i = 0; i < combination.getCards().size(); i++) {
            kickers.add(combination.getCards().get(i).getRankToInt());
        }

        // fill kickers with other card ranks
        for (int i = 0; i < 5 - combination.getCards().size(); i++) {
            kickers.add(temp.get(i).getRankToInt());
        }

        return new Kicker(kickers); // TcAs=Th2d, J T 9 8 7, J T 9 8 7
    }

}