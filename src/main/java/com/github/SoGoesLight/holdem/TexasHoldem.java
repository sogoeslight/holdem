package com.github.SoGoesLight.holdem;

import com.github.SoGoesLight.holdem.domain.Game;
import com.github.SoGoesLight.holdem.domain.GameResult;
import com.github.SoGoesLight.holdem.rank.CompositeRankChecker;
import com.github.SoGoesLight.holdem.rank.checkers.*;
import com.github.SoGoesLight.holdem.services.Evaluator;
import com.github.SoGoesLight.holdem.services.GameParser;
import com.github.SoGoesLight.holdem.services.ResultPrinter;

import java.util.List;
import java.util.Scanner;

public class TexasHoldem {
    private final GameParser parser;
    private final Evaluator evaluator;
    private final ResultPrinter printer;

    public TexasHoldem(GameParser parser, Evaluator evaluator, ResultPrinter printer) {
        this.parser = parser;
        this.evaluator = evaluator;
        this.printer = printer;
    }

    public static void main(String[] args) {
        GameParser parser = new GameParser();
        CompositeRankChecker rankChecker = new CompositeRankChecker(List.of(new RoyalFlushChecker(),
                new StraightFlushChecker(), new FourOfAKindChecker(), new FullHouseChecker(),
                new FlushChecker(), new StraightChecker(), new ThreeOfAKindChecker(),
                new TwoPairsChecker(), new OnePairChecker(), new HighCardChecker()));
        Evaluator evaluator = new Evaluator(rankChecker);
        ResultPrinter printer = new ResultPrinter();
        new TexasHoldem(parser, evaluator, printer).play();
    }

    private void play() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            try {
                String input = scanner.nextLine();
                Game game = parser.parse(input);
                GameResult result = evaluator.process(game);
                printer.print(result.evaluateResultString());
            } catch (Exception e) {
                printer.print(e);
            }
        }
    }

}
