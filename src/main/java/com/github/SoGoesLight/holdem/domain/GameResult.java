package com.github.SoGoesLight.holdem.domain;

import com.github.SoGoesLight.holdem.enums.CombinationRank;
import com.github.SoGoesLight.holdem.rank.PlayerComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameResult {

    private final List<Player> result;
    private String resultString = "";
    private final PlayerComparator comparator = new PlayerComparator();

    public GameResult(List<Player> result) {
        this.result = result;
    }

    public List<Player> getResult() {
        return result;
    }

    public String evaluateResultString() {
        List<Player> playersWithSameRank;
        List<Player> tempList = new ArrayList<>(result);
        List<Player> resultList = new ArrayList<>();

        for (Player player : result) {
            CombinationRank rank = player.getRank();

            playersWithSameRank = tempList.stream()
                    .filter(p -> p.getRank().equals(rank))
                    .collect(Collectors.toList());
            tempList.removeAll(comparator.compared(playersWithSameRank, rank));
            resultList.addAll(comparator.compared(playersWithSameRank, rank));
        }

        for (int i = 0; i < resultList.size() - 1; i++) {
            List<Integer> first = resultList.get(i).getKicker().getKickersList();
            List<Integer> second = resultList.get(i + 1).getKicker().getKickersList();
            if (!first.equals(second)) {
                resultString += resultList.get(i).getHandString() + " ";
            } else {
                resultString += resultList.get(i).getHandString() + "=";
            }
        }
        resultString += resultList.get(resultList.size() - 1).getHandString() + "\n";

        return resultString;
    }

}
