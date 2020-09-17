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

class ThreeOfAKindCheckerTest {

    private final RankChecker subject = new ThreeOfAKindChecker();

    public static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(createSevenCards("4cKs3h8s7s", "4d4s"), createCardList("4c4d4s")),
                Arguments.of(createSevenCards("TcKsTh8sTs", "Qd3s"), createCardList("TcThTs")),
                Arguments.of(createSevenCards("QdQcQhJsTs", "4d3s"), createCardList("QdQcQh"))
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void check_happy_path(SevenCards sevenCards, List<Card> result) {
        Optional<Combination> actual = subject.check(sevenCards);

        assertThat(actual).isPresent();
        assertThat(actual.get().getCards()).usingRecursiveComparison().isEqualTo(result);
        assertThat(actual.get().getRank()).isEqualTo(CombinationRank.THREE_OF_A_KIND);
    }

    @Test
    void check_bad_scenario() {
        SevenCards result = createSevenCards("2d4c6h8sTd","JcKh");

        Optional<Combination> actual = subject.check(result);

        assertThat(actual).isEmpty();
    }
}