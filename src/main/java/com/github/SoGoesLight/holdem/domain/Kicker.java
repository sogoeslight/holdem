package com.github.SoGoesLight.holdem.domain;

import java.util.Arrays;
import java.util.List;

public class Kicker {
    private final List<Integer> kickers;

    public Kicker(List<Integer> kickers) {
        this.kickers = kickers;
    }

    public List<Integer> getKickersList() {
        return kickers;
    }

    @Override
    public String toString() {
        return Arrays.toString(kickers.toArray());
    }

}
