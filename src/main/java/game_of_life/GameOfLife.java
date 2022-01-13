package game_of_life;

import java.util.Objects;

import static game_of_life.Validate.validPosition;

public class GameOfLife {
    private final Board board;

    private GameOfLife(Board board) {
        this.board = board;
    }

    public Cell[][] getCellMatrix() {
        return board.cellMatrix();
    }

    public Cell getCell(Position position) {
        validPosition(position, board.matrixSize());
        return board.cell(position);
    }

    public void render() {
        board.render();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameOfLife that = (GameOfLife) o;
        return Objects.equals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board);
    }

    public static class GameOfLifeBuilder {
        private final Board board;
        private final MatrixSize matrixSize;

        public GameOfLifeBuilder(MatrixSize matrixSize) {
            this.matrixSize = matrixSize;
            this.board = new Board(matrixSize);
        }

        public GameOfLifeBuilder cellAlive(Position position) {
            validPosition(position, matrixSize);
            board.cell(position).setAlive(true);
            board.cell(position).setAliveLastRender(true);
            return this;
        }

        public GameOfLife build() {
            return new GameOfLife(board);
        }
    }
}
