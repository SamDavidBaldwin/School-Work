package main;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    private Score score;

    @BeforeEach
    void setUp() {
        this.score = new Score();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getScore() {
        String value = score.getScore(1);
        assertEquals(value,"_");
    }

    @Test
    void updateScore() {
        String testString = "_o__o__oooo";
        score.updateScore(1, "o__o__oooo");
        assertEquals(score.getScore(1),testString );
    }
}