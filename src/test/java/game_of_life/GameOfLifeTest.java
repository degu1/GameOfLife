package game_of_life;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameOfLifeTest {

    @Test
    void empty3x3ReturnsEmpty3x3() {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(new MatrixSize(3, 3))
                .build();

        GameOfLife expected = new GameOfLife.GameOfLifeBuilder(new MatrixSize(3, 3))
                .build();

        assertThat(gameOfLife.getCellMatrix()).isEqualTo(expected.getCellMatrix());
    }

    @Test
    void aliveCellNoNeighboursDiesWhenRender() {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(new MatrixSize(3, 3))
                .cellAlive(new Position(1, 1))
                .build();
        gameOfLife.renderNextGeneration();

        Cell expected = new Cell(new Position(1, 1));

        assertThat(gameOfLife.getCell(new Position(1, 1))).isEqualTo(expected);
    }

    @Test
    void aliveCellTwoNeighboursLivesWhenRender() {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(new MatrixSize(3, 3))
                .cellAlive(new Position(1, 1))
                .cellAlive(new Position(0, 1))
                .cellAlive(new Position(0, 2))
                .build();
        gameOfLife.renderNextGeneration();

        Cell expected = new Cell(new Position(1, 1));
        expected.setAlive(true);

        assertThat(gameOfLife.getCell(new Position(1, 1))).isEqualTo(expected);
    }

    @Test
    void aliveCellThreeNeighboursLivesWhenRender() {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(new MatrixSize(3, 3))
                .cellAlive(new Position(1, 1))
                .cellAlive(new Position(0, 1))
                .cellAlive(new Position(0, 2))
                .cellAlive(new Position(1, 0))
                .build();
        gameOfLife.renderNextGeneration();

        Cell expected = new Cell(new Position(1, 1));
        expected.setAlive(true);

        assertThat(gameOfLife.getCell(new Position(1, 1))).isEqualTo(expected);
    }

    @Test
    void aliveCellOneNeighboursDiesWhenRender() {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(new MatrixSize(3, 3))
                .cellAlive(new Position(1, 1))
                .cellAlive(new Position(0, 1))
                .build();
        gameOfLife.renderNextGeneration();

        Cell expected = new Cell(new Position(1, 1));

        assertThat(gameOfLife.getCell(new Position(1, 1))).isEqualTo(expected);
    }

    @Test
    void aliveCellFourNeighboursDiesWhenRender() {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(new MatrixSize(3, 3))
                .cellAlive(new Position(1, 1))
                .cellAlive(new Position(0, 1))
                .cellAlive(new Position(0, 2))
                .cellAlive(new Position(1, 0))
                .cellAlive(new Position(1, 2))
                .build();
        gameOfLife.renderNextGeneration();

        Cell expected = new Cell(new Position(1, 1));

        assertThat(gameOfLife.getCell(new Position(1, 1))).isEqualTo(expected);
    }

    @Test
    void deadCellWithThreeNeighboursAliveWhenRender() {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(new MatrixSize(3, 3))
                .cellAlive(new Position(0, 2))
                .cellAlive(new Position(1, 0))
                .cellAlive(new Position(1, 2))
                .build();
        gameOfLife.renderNextGeneration();

        Cell expected = new Cell(new Position(1, 1));
        expected.setAlive(true);

        assertThat(gameOfLife.getCell(new Position(1, 1))).isEqualTo(expected);
    }

    @Test
    void deadCellWithTwoNeighboursStaysDeadWhenRender() {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(new MatrixSize(3, 3))
                .cellAlive(new Position(0, 2))
                .cellAlive(new Position(1, 0))
                .build();
        gameOfLife.renderNextGeneration();

        Cell expected = new Cell(new Position(1, 1));

        assertThat(gameOfLife.getCell(new Position(1, 1))).isEqualTo(expected);
    }

    @Test
    void deadCellWithFourNeighboursStaysDeadWhenRender() {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(new MatrixSize(3, 3))
                .cellAlive(new Position(0, 2))
                .cellAlive(new Position(1, 0))
                .cellAlive(new Position(1, 2))
                .cellAlive(new Position(2, 2))
                .build();
        gameOfLife.renderNextGeneration();

        Cell expected = new Cell(new Position(1, 1));

        assertThat(gameOfLife.getCell(new Position(1, 1))).isEqualTo(expected);
    }

    @Test
    void matrix10x10with3liveCellsAnd4LiveCellsAfterRender() {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(new MatrixSize(10, 10))
                .cellAlive(new Position(0, 0))
                .cellAlive(new Position(0, 1))
                .cellAlive(new Position(1, 0))
                .build();
        gameOfLife.renderNextGeneration();

        GameOfLife expected = new GameOfLife.GameOfLifeBuilder(new MatrixSize(10, 10))
                .cellAlive(new Position(0, 0))
                .cellAlive(new Position(0, 1))
                .cellAlive(new Position(1, 0))
                .cellAlive(new Position(1, 1))
                .build();

        assertThat(gameOfLife).isEqualTo(expected);
    }

    @Test
    void aliveCellInPosition0x0In3x3Matrix() {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(new MatrixSize(3, 3))
                .cellAlive(new Position(0, 0))
                .build();
        gameOfLife.renderNextGeneration();

        GameOfLife expected = new GameOfLife.GameOfLifeBuilder(new MatrixSize(3, 3))
                .build();

        assertThat(gameOfLife).isEqualTo(expected);
    }

    @Test
    void aliveCellInPosition0x2In3x3Matrix() {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(new MatrixSize(3, 3))
                .cellAlive(new Position(0, 2))
                .build();
        gameOfLife.renderNextGeneration();

        GameOfLife expected = new GameOfLife.GameOfLifeBuilder(new MatrixSize(3, 3))
                .build();

        assertThat(gameOfLife).isEqualTo(expected);
    }

    @Test
    void aliveCellInPosition2x0In3x3Matrix() {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(new MatrixSize(3, 3))
                .cellAlive(new Position(2, 0))
                .build();
        gameOfLife.renderNextGeneration();

        GameOfLife expected = new GameOfLife.GameOfLifeBuilder(new MatrixSize(3, 3))
                .build();

        assertThat(gameOfLife).isEqualTo(expected);
    }

    @Test
    void aliveCellInPosition2x2In3x3Matrix() {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(new MatrixSize(3, 3))
                .cellAlive(new Position(2, 2))
                .build();
        gameOfLife.renderNextGeneration();

        GameOfLife expected = new GameOfLife.GameOfLifeBuilder(new MatrixSize(3, 3))
                .build();

        assertThat(gameOfLife).isEqualTo(expected);
    }

    @Test
    void twoRendersOf3AliveIn5x5Matrix() {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(new MatrixSize(5, 5))
                .cellAlive(new Position(2, 1))
                .cellAlive(new Position(2, 2))
                .cellAlive(new Position(2, 3))
                .build();
        gameOfLife.renderNextGeneration();
        gameOfLife.renderNextGeneration();

        GameOfLife expected = new GameOfLife.GameOfLifeBuilder(new MatrixSize(5, 5))
                .cellAlive(new Position(2, 1))
                .cellAlive(new Position(2, 2))
                .cellAlive(new Position(2, 3))
                .build();

        assertThat(gameOfLife).isEqualTo(expected);
    }

    @Test
    void Matrix0x1ThrowsException() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new GameOfLife.GameOfLifeBuilder(new MatrixSize(0, 1)).build());
    }

    @Test
    void Matrix1x0ThrowsException() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new GameOfLife.GameOfLifeBuilder(new MatrixSize(1, 0)).build());
    }

    @Test
    void throwsNullPointExceptionWhenPositionIsNull() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() ->
                new GameOfLife.GameOfLifeBuilder(new MatrixSize(3, 3))
                        .cellAlive(null)
                        .build());
    }

    @Test
    void throwsExceptionWhenPositionRowNotInMatrix() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                new GameOfLife.GameOfLifeBuilder(new MatrixSize(3, 3))
                        .cellAlive(new Position(4, 0))
                        .build());
    }

    @Test
    void throwsExceptionWhenPositionColumnNotInMatrix() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                new GameOfLife.GameOfLifeBuilder(new MatrixSize(3, 3))
                        .cellAlive(new Position(0, 4))
                        .build());
    }

    @Test
    void notThrowsExceptionWhenRowColumnInMatrix() {
        assertThatNoException().isThrownBy(() ->
                new GameOfLife.GameOfLifeBuilder(new MatrixSize(3, 3))
                        .cellAlive(new Position(0, 2))
                        .build());
    }

    @Test
    void notThrowsExceptionWhenPositionColumnInMatrix() {
        assertThatNoException().isThrownBy(() ->
                new GameOfLife.GameOfLifeBuilder(new MatrixSize(3, 3))
                        .cellAlive(new Position(0, 2))
                        .build());
    }

    @Test
    void getCellThrowsExceptionWhenPositionIsOutsideMatrix() {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(new MatrixSize(3, 3))
                .build();

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> gameOfLife.getCell(new Position(4, 4)));
    }

    @Test
    void test100x100() {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(new MatrixSize(100, 100))
                .build();

        long start = System.currentTimeMillis();
        gameOfLife.renderNextGeneration();
        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("Execution time of renderNextGeneration " + executionTime + "milliseconds");

        assertThat(executionTime).isLessThan(500);
    }

    @Test
    void getCellMatrixNotReturnNull() {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(new MatrixSize(10, 4))
                .build();

        assertThat(gameOfLife.getCellMatrix()).isNotNull();

    }
}