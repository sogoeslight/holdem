package com.github.SoGoesLight.holdem.domain;

import com.github.SoGoesLight.holdem.enums.CardRank;
import com.github.SoGoesLight.holdem.enums.CardSuit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class SevenCards {

    private final Board board;
    private final Hand hand;
    private final List<Card> cards;

    public SevenCards(Board board, Hand hand) {
        this.board = board;
        this.hand = hand;
        this.cards = Stream.concat(
                board.getCards().stream(),
                hand.getCards().stream()
        ).collect(toList());
    }

    public List<Card> getCards() {
        return cards;
    }

    public Hand getHand() {
        return hand;
    }

    public Board getBoard() {
        return board;
    }

    public Optional<List<Card>> getCardSequence(Boolean compareSuit) {
        List<Card> sequenceList = new ArrayList<>();
        Optional<Card> previousCard = Optional.empty();

        // checking whether board or hand contains Ace
        Optional<Card> boardAce = getBoard().getCards().stream()
                .filter(c -> c.getRank().equals(CardRank.ACE))
                .findFirst();
        Optional<Card> handAce = getHand().getCards().stream()
                .filter(c -> c.getRank().equals(CardRank.ACE))
                .findFirst();


        List<Card> sortedCards;
        if (compareSuit) {
            sortedCards = cards.stream()
                    .sorted(Comparator.comparing(Card::getSuit).thenComparing(Card::getRank).reversed())
                    .collect(Collectors.toList());
        } else {
            sortedCards = cards.stream()
                    .sorted(Comparator.comparing(Card::getRank).reversed())
                    .collect(Collectors.toList());
        }

        for (Card card : sortedCards) {
            if (previousCard.isPresent()) {

                if ((previousCard.get().getRankToInt() - card.getRankToInt()) == 1) {
                    if (!compareSuit || previousCard.get().getSuit().equals(card.getSuit())) {
                        if (sequenceList.size() == 0) {
                            sequenceList.add(previousCard.get());
                        }

                        sequenceList.add(card);

                        // Checking aces
                        if (sequenceList.size() == 4 && card.getRank() == CardRank.TWO) {
                            if (compareSuit) {
                                CardSuit actualSuit = sequenceList.get(3).getSuit();
                                Optional<Card> correctSuit = getHand().getCards().stream()
                                        .filter(c -> c.getSuit().equals(actualSuit) && c.getRank().equals(CardRank.ACE))
                                        .findFirst();
                                if (correctSuit.isPresent())
                                    addAceIfNotPresentInSequence(sequenceList, correctSuit.get());
                            }
                            if (boardAce.isPresent())
                                addAceIfNotPresentInSequence(sequenceList, boardAce.get());
                            if (boardAce.isEmpty() && handAce.isPresent())
                                addAceIfNotPresentInSequence(sequenceList, handAce.get());
                        }

                        if (sequenceList.size() == 5) {
                            return Optional.of(sequenceList);
                        }
                    }
                }

                if (previousCard.get().getRankToInt() - card.getRankToInt() > 1
                        || previousCard.get().getRankToInt() - card.getRankToInt() < 0) {
                    sequenceList.clear();
                }
            }
            previousCard = Optional.of(card);

        }

        return Optional.empty();
    }

    private void addAceIfNotPresentInSequence(List<Card> sequenceList, Card ace) {
        if (sequenceList.stream().noneMatch(c -> c.getRank().equals(CardRank.ACE))) {
            sequenceList.add(ace);
        }
    }

}
