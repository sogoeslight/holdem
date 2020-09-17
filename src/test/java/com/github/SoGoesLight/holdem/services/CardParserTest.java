package com.github.SoGoesLight.holdem.services;

import com.github.SoGoesLight.holdem.domain.Card;
import com.github.SoGoesLight.holdem.enums.CardRank;
import com.github.SoGoesLight.holdem.enums.CardSuit;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CardParserTest {

    public static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of("4cKs4h8s7s", List.of(new Card(CardSuit.CLUBS, CardRank.FOUR),
                                                   new Card(CardSuit.SPADES, CardRank.KING),
                                                   new Card(CardSuit.HEARTS, CardRank.FOUR),
                                                   new Card(CardSuit.SPADES, CardRank.EIGHT),
                                                   new Card(CardSuit.SPADES, CardRank.SEVEN))),
                Arguments.of("Ad4s", List.of(new Card(CardSuit.DIAMONDS, CardRank.ACE),
                                             new Card(CardSuit.SPADES, CardRank.FOUR))),
                Arguments.of("KhKd", List.of(new Card(CardSuit.HEARTS, CardRank.KING),
                                             new Card(CardSuit.DIAMONDS, CardRank.KING)))
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void check_happy_path(String str, List<Card> result) {
        List<Card> actual = CardParser.parseCards(str);

        assertThat(actual).usingRecursiveComparison().isEqualTo(result);
    }

}