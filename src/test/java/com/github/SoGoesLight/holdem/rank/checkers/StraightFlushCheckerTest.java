package com.github.SoGoesLight.holdem.rank.checkers;

import com.github.SoGoesLight.holdem.domain.Card;
import com.github.SoGoesLight.holdem.domain.Combination;
import com.github.SoGoesLight.holdem.domain.SevenCards;
import com.github.SoGoesLight.holdem.enums.CombinationRank;
import com.github.SoGoesLight.holdem.rank.RankChecker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.github.SoGoesLight.holdem.helpers.TestHelper.createCardList;
import static com.github.SoGoesLight.holdem.helpers.TestHelper.createSevenCards;
import static org.assertj.core.api.Assertions.assertThat;

class StraightFlushCheckerTest {

    private final RankChecker subject = new StraightFlushChecker();

    public static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(createSevenCards("4dKs4h8d7d", "5d6d"), createCardList("8d7d6d5d4d")),
                Arguments.of(createSevenCards("3s4s5s6s7s", "TdJs"), createCardList("7s6s5s4s3s")),
                Arguments.of(createSevenCards("4d5dQhQs2d", "Ad3d"), createCardList("5d4d3d2dAd"))
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void check_happy_path(SevenCards sevenCards, List<Card> result) {
        Optional<Combination> actual = subject.check(sevenCards);

        assertThat(actual).isPresent();
        assertThat(actual.get().getCards()).usingRecursiveComparison().isEqualTo(result);
        assertThat(actual.get().getRank()).isEqualTo(CombinationRank.STRAIGHT_FLUSH);
    }

    @Test
    void check_bad_scenario() {
        SevenCards result = createSevenCards("2d4c6h8sTd","JcKh");

        Optional<Combination> actual = subject.check(result);

        assertThat(actual).isEmpty();
    }
}