package com.kirilov.interview.solutions.janken;

import java.util.HashMap;
import java.util.Map;

import static com.kirilov.interview.solutions.janken.GameResult.*;
import static com.kirilov.interview.solutions.janken.Janken.*;

//Here’s the “rock, paper, scissors"
public class JankenGame {

    private static final Map<Janken, Janken> winRules = new HashMap<>();

    static {
        winRules.put(ROCK, SCISSORS);
        winRules.put(SCISSORS, PAPER);
        winRules.put(PAPER, ROCK);
    }

    public static GameResult play(Janken player1, Janken player2) {

        //assert player1 and player2 are not null
        if (player1 == null || player2 == null) {
            throw new IllegalArgumentException();
        }

        if (player1 == player2) {
            return DRAW;
        }

        if (winRules.get(player1) == player2) {
            return WIN;
        } else {
            return LOSS;
        }
    }
}

