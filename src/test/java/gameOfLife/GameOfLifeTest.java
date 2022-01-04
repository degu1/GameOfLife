package gameOfLife;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeTest {

    @Test
    void empty3x3ReturnsEmpty3x3() {
        GameOfLife gameOfLife = new GameOfLife(3, 3);

        GameOfLife expected = new GameOfLife(3, 3);

        assertThat(gameOfLife.getCellMatrix()).isEqualTo(expected.getCellMatrix());
    }

    @Test
    void aliveCellNoNeighboursDiesWhenRender() {
        GameOfLife gameOfLife = new GameOfLife(3, 3);
        gameOfLife.setCellAlive(new Position(1, 1), true);
        gameOfLife.render();

        Cell expected = new Cell(new Position(1, 1));

        assertThat(gameOfLife.getCell(new Position(1, 1))).isEqualTo(expected);
    }

    @Test
    void aliveCellTwoNeighboursLivesWhenRender() {
        GameOfLife gameOfLife = new GameOfLife(3, 3);
        gameOfLife.setCellAlive(new Position(1, 1), true);
        gameOfLife.setCellAlive(new Position(0, 1), true);
        gameOfLife.setCellAlive(new Position(0, 2), true);
        gameOfLife.render();

        Cell expected = new Cell(new Position(1, 1));
        expected.setAlive(true);

        assertThat(gameOfLife.getCell(new Position(1, 1))).isEqualTo(expected);
    }
}