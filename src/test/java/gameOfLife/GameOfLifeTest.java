package gameOfLife;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameOfLifeTest {

    @Test
    void empty3x3ReturnsEmpty3x3() {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(3, 3)
                .build();

        GameOfLife expected = new GameOfLife.GameOfLifeBuilder(3, 3)
                .build();

        assertThat(gameOfLife.getCellMatrix()).isEqualTo(expected.getCellMatrix());
    }

    @Test
    void aliveCellNoNeighboursDiesWhenRender() {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(3, 3)
                .cellAlive(new Position(1, 1))
                .build();
        gameOfLife.render();

        Cell expected = new Cell(new Position(1, 1));

        assertThat(gameOfLife.getCell(new Position(1, 1))).isEqualTo(expected);
    }

    @Test
    void aliveCellTwoNeighboursLivesWhenRender() {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(3, 3)
                .cellAlive(new Position(1, 1))
                .cellAlive(new Position(0, 1))
                .cellAlive(new Position(0, 2))
                .build();

        gameOfLife.render();

        Cell expected = new Cell(new Position(1, 1));
        expected.setAlive(true);

        assertThat(gameOfLife.getCell(new Position(1, 1))).isEqualTo(expected);
    }

    @Test
    void aliveCellThreeNeighboursLivesWhenRender() {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(3, 3)
                .cellAlive(new Position(1, 1))
                .cellAlive(new Position(0, 1))
                .cellAlive(new Position(0, 2))
                .cellAlive(new Position(1, 0))
                .build();

        gameOfLife.render();

        Cell expected = new Cell(new Position(1, 1));
        expected.setAlive(true);

        assertThat(gameOfLife.getCell(new Position(1, 1))).isEqualTo(expected);
    }

    @Test
    void aliveCellOneNeighboursDiesWhenRender() {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(3, 3)
                .cellAlive(new Position(1, 1))
                .cellAlive(new Position(0, 1))
                .build();

        gameOfLife.render();

        Cell expected = new Cell(new Position(1, 1));

        assertThat(gameOfLife.getCell(new Position(1, 1))).isEqualTo(expected);
    }

    @Test
    void aliveCellFourNeighboursDiesWhenRender() {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(3, 3)
                .cellAlive(new Position(1, 1))
                .cellAlive(new Position(0, 1))
                .cellAlive(new Position(0, 2))
                .cellAlive(new Position(1, 0))
                .cellAlive(new Position(1, 2))
                .build();

        gameOfLife.render();

        Cell expected = new Cell(new Position(1, 1));

        assertThat(gameOfLife.getCell(new Position(1, 1))).isEqualTo(expected);
    }
}