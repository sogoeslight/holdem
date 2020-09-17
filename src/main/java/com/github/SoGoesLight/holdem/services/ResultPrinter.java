package com.github.SoGoesLight.holdem.services;

public class ResultPrinter {

    public void print(String result) {
        System.out.print(result);
    }

    public void print(Exception e) {
        System.out.println(e.getMessage());
    }

}
