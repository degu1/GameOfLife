package gameOfLife;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeTest {

    @Test
    void empty3x3ReturnsEmpty3x3() {
        GameOfLife gameOfLife = new GameOfLife(3, 3);
        Cell[][] expected = new Cell[3][3];

        assertThat(gameOfLife.getCellMatrix()).isEqualTo(expected);
    }
}