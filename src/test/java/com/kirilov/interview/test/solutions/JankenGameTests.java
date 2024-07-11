package com.kirilov.interview.test.solutions;

import com.kirilov.interview.solutions.janken.JankenGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.kirilov.interview.solutions.janken.GameResult.*;
import static com.kirilov.interview.solutions.janken.Janken.*;

public class JankenGameTests {

    @Test
    public void error() {
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> JankenGame.play(ROCK, null));
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> JankenGame.play(null, PAPER));
    }

    @Test
    public void draw() {
        Assertions.assertEquals(DRAW, JankenGame.play(ROCK, ROCK));
        Assertions.assertEquals(DRAW, JankenGame.play(PAPER, PAPER));
        Assertions.assertEquals(DRAW, JankenGame.play(SCISSORS, SCISSORS));
    }

    @Test
    public void win() {
        Assertions.assertEquals(WIN, JankenGame.play(ROCK, SCISSORS));
        Assertions.assertEquals(WIN, JankenGame.play(SCISSORS, PAPER));
        Assertions.assertEquals(WIN, JankenGame.play(PAPER, ROCK));
    }

    @Test
    public void lose() {
        Assertions.assertEquals(LOSS, JankenGame.play(SCISSORS, ROCK));
        Assertions.assertEquals(LOSS, JankenGame.play(PAPER, SCISSORS));
        Assertions.assertEquals(LOSS, JankenGame.play(ROCK, PAPER));
    }
}
