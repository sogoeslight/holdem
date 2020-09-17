package com.github.SoGoesLight.holdem;

import com.github.SoGoesLight.holdem.domain.Game;
import com.github.SoGoesLight.holdem.domain.GameResult;
import com.github.SoGoesLight.holdem.rank.CompositeRankChecker;
import com.github.SoGoesLight.holdem.rank.checkers.*;
import com.github.SoGoesLight.holdem.services.Evaluator;
import com.github.SoGoesLight.holdem.services.GameParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class TexasHoldemTest {

        private final static GameParser parser = new GameParser();
        CompositeRankChecker rankChecker = new CompositeRankChecker(List.of(new RoyalFlushChecker(),
                new StraightFlushChecker(), new FourOfAKindChecker(), new FullHouseChecker(),
                new FlushChecker(), new StraightChecker(), new ThreeOfAKindChecker(),
                new TwoPairsChecker(), new OnePairChecker(), new HighCardChecker()));
        private final Evaluator evaluator = new Evaluator(rankChecker);

    public static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(parser.parse("3s7sAhQhTd 8h4s 9c4h Kd9s 3hTs 9h6c TcKh 6sKs 8cAs 2c6h"),
                        "2c6h 8h4s 9c4h=9h6c 6sKs Kd9s TcKh 8cAs 3hTs\n"),
                Arguments.of(parser.parse("5c6dAcAsQs Ks4c KdJs 2hAh Kh4h Kc7h 6h7d 2cJc"),
                        "2cJc Kh4h=Ks4c Kc7h KdJs 6h7d 2hAh\n"),
                Arguments.of(parser.parse("3d4s5dJsQd 5c4h 7sJd KcAs 9h7h 2dTc Qh8c TsJc"),
                        "9h7h 2dTc KcAs 7sJd TsJc Qh8c 5c4h\n"),
                Arguments.of(parser.parse("2h5c8sAsKc Qs9h KdQh 3cKh Jc6s"),
                        "Jc6s Qs9h 3cKh KdQh\n"),
                Arguments.of(parser.parse("4c5c6c9cJs 6hTh 8d2s 5h9s KcTc 3d4d 3s8c 7hKs 9h5s"),
                        "8d2s 7hKs 3d4d 6hTh 5h9s=9h5s 3s8c KcTc\n"),
                Arguments.of(parser.parse("3d4c5c8hTc Ad8d 7cQc Kd8c Jd6d Kc4s 9d3h 9c7s Th7h"),
                        "9c7s Jd6d 9d3h Kc4s Kd8c Ad8d Th7h 7cQc\n"),
                Arguments.of(parser.parse("7s8d8h9sJh AcKc Th2d 7d5c 5h4d 5dJd Js9d TcAs 7cKs 3dQc"),
                        "5h4d 3dQc AcKc 7d5c 7cKs 5dJd Js9d TcAs=Th2d\n"),
                Arguments.of(parser.parse("3h5dJcJsTc 3sQh 2s5c 6cAc 7cKs"),
                        "7cKs 6cAc 3sQh 2s5c\n"),
                Arguments.of(parser.parse("3d9hJcTdTs 9d5h AcAh 9c3s Ad6c"),
                        "Ad6c 9c3s=9d5h AcAh\n"),
                Arguments.of(parser.parse("2d3h8c8s9s 6cAh AcQc 5dKh 7d5s 7c3d 6hQs 7hTs 5h9d Qd8d"),
                        "7d5s 7hTs 6hQs 5dKh 6cAh AcQc 7c3d 5h9d Qd8d\n"),
                Arguments.of(parser.parse("2h6d8sJcJs 4h2c 4d4c Ac9c Qc9h Qs2s"),
                        "Qc9h Ac9c 4h2c Qs2s 4d4c\n"),
                Arguments.of(parser.parse("4d4s8dKdKs 8sJd Kh8c 2s2d 7h2c 3c6s Qc5h"),
                        "2s2d=3c6s=7h2c Qc5h 8sJd Kh8c\n"),
                Arguments.of(parser.parse("5h7c7d7sQd Js5s Qc9h 6hTc 2c3c 2dJh Kh2h 9sAh"),
                        "2c3c 6hTc 2dJh Kh2h 9sAh Js5s Qc9h\n"),
                Arguments.of(parser.parse("9h9sAdAsTd 7c7h Qd5s KsKc ThAh 5h3c 3s3d QhTc Qc8s 6c4d"),
                        "3s3d=5h3c=6c4d=7c7h Qc8s=Qd5s QhTc KsKc ThAh\n"),
                Arguments.of(parser.parse("4c7d8hQcQd 4d6h Qs3c 4h8d Ah3h 6c2c Ts2s"),
                        "6c2c Ts2s Ah3h 4d6h 4h8d Qs3c\n"),
                Arguments.of(parser.parse("4c4d4h5h5s Kd6s QcTc KsTd 8s7c AdJc 9h9d Kh8h"),
                        "8s7c=AdJc=Kd6s=Kh8h=KsTd=QcTc 9h9d\n"),
                Arguments.of(parser.parse("2c2h2s3dKc 9c3h 3cKh"),
                        "9c3h 3cKh\n"),
                Arguments.of(parser.parse("2s9cKsQcTc 9d4h 5h3d 8s2h 3sJc 3cAh JhAs AcQh 4c8c 8h8d"),
                        "5h3d 3cAh 8s2h 8h8d 9d4h AcQh 3sJc JhAs 4c8c\n"),
                Arguments.of(parser.parse("4d4hJdKdQd 6hAs 3c5c TcJs 2h3h QhKs"),
                        "2h3h=3c5c 6hAs TcJs QhKs\n")
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void play_happy_path(Game game, String result) {
        GameResult gameResult = evaluator.process(game);
        String actual = gameResult.evaluateResultString();

        assertThat(actual).isEqualTo(result);
    }

}