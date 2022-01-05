package gameOfLife;

import java.util.Arrays;

import static java.lang.Math.pow;
import static gameOfLife.Validate.notNull;
import static gameOfLife.Validate.isBetween;
import static gameOfLife.Validate.validPosition;

public class GameOfLife {
    private final Cell[][] cellMatrix;
    private final Cell[][] lastCellMatrix;
    private final MatrixSize matrixSize;

    private GameOfLife(Cell[][] cellMatrix, Cell[][] lastCellMatrix, MatrixSize matrixSize) {
        this.cellMatrix = cellMatrix;
        this.lastCellMatrix = lastCellMatrix;
        this.matrixSize = matrixSize;
    }

    public Cell[][] getCellMatrix() {
        return cellMatrix;
    }

    public Cell getCell(Position position) {
        return cellMatrix[position.row()][position.column()];
    }

    public MatrixSize getMatrixSize() {
        return matrixSize;
    }

    public void render() {
        renderAllCells();
        updateLastCellMatrix();
    }

    private void renderAllCells() {
        Arrays.stream(cellMatrix).flatMap(Arrays::stream)
                .forEach(cell -> cell.renderCell(numberOfNeighbors(cell)));
    }

    private void updateLastCellMatrix() {
        for (int row = 0; row < cellMatrix.length; row++) {
            for (int col = 0; col < cellMatrix[0].length; col++) {
                lastCellMatrix[row][col].setAlive(cellMatrix[row][col].isAlive());
            }
        }
    }

    private int numberOfNeighbors(Cell currentCell) {
        return Arrays.stream(lastCellMatrix).flatMap(Arrays::stream)
                .filter(cell -> isNeighbor(currentCell.position(), cell.position()))
                .filter(Cell::isAlive)
                .mapToInt(cell -> 1)
                .sum();
    }

    private boolean isNeighbor(Position position, Position position2) {
        return isBetween(squareDistanceOfTwoPositions(position, position2), 0, 4);
    }

    private double squareDistanceOfTwoPositions(Position position, Position position2) {
        return pow(position.row() - position2.row(), 2) + pow(position.column() - position2.column(), 2);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameOfLife that = (GameOfLife) o;
        for (int i = 0; i < cellMatrix[0].length; i++) {
            if (!Arrays.equals(cellMatrix[i], that.cellMatrix[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(cellMatrix);
    }

    public static class GameOfLifeBuilder {
        private final Cell[][] cellMatrix;
        private final Cell[][] lastCellMatrix;
        MatrixSize matrixSize;

        public GameOfLifeBuilder(MatrixSize matrixSize) {
            this.matrixSize = matrixSize;
            this.cellMatrix = new Cell[matrixSize.rows()][matrixSize.columns()];
            this.lastCellMatrix = new Cell[matrixSize.rows()][matrixSize.columns()];
            populateMatrix(matrixSize.rows(), matrixSize.columns());
        }

        private void populateMatrix(int rows, int columns) {
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    cellMatrix[row][col] = new Cell(new Position(row, col));
                    lastCellMatrix[row][col] = new Cell(new Position(row, col));
                }
            }
        }

        public GameOfLifeBuilder cellAlive(Position position) {
            validPosition(notNull(position), matrixSize);
            cellMatrix[position.row()][position.column()].bringAlive();
            lastCellMatrix[position.row()][position.column()].bringAlive();
            return this;
        }

        public GameOfLife build() {
            return new GameOfLife(cellMatrix, lastCellMatrix, matrixSize);
        }

    }
}
