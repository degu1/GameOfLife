package gameOfLife;

import java.util.Arrays;
import static java.lang.Math.pow;

public class GameOfLife {
    private final Cell[][] cellMatrix;
    private final Cell[][] lastCellMatrix;

    private GameOfLife(Cell[][] cellMatrix, Cell[][] lastCellMatrix) {
        this.cellMatrix = cellMatrix;
        this.lastCellMatrix = lastCellMatrix;
    }

    public Cell[][] getCellMatrix() {
        return cellMatrix;
    }

    public Cell getCell(Position position) {
        return cellMatrix[position.row()][position.column()];
    }

    public void render() {
        renderAllCells();
        updateLastCellMatrix();
    }

    private void renderAllCells() {
        Arrays.stream(cellMatrix).flatMap(Arrays::stream)
                .forEach(cell -> renderCell(cell));
    }

    private void updateLastCellMatrix() {
        for (int row = 0; row < cellMatrix.length; row++) {
            for (int col = 0; col < cellMatrix[0].length; col++) {
                lastCellMatrix[row][col].setAlive(cellMatrix[row][col].isAlive());
            }
        }
    }

    private void renderCell(Cell cell) {
        if (cell.isAlive()) {
            renderAliveCell(cell);
        } else {
            renderDeadCell(cell);
        }
    }

    private void renderDeadCell(Cell cell) {
        int numberOfNeighbours = numberOfNeighbors(cell);
        if (numberOfNeighbours == 3) {
            cell.setAlive(true);
        }
    }

    private void renderAliveCell(Cell cell) {
        int numberOfNeighbours = numberOfNeighbors(cell);
        if (2 > numberOfNeighbours || numberOfNeighbours > 3) {
            cell.setAlive(false);
        }
    }

    public int numberOfNeighbors(Cell currentCell) {
        return Arrays.stream(lastCellMatrix).flatMap(Arrays::stream)
                .filter(cell -> isNeighbor(currentCell.position(), cell.position()))
                .filter(Cell::isAlive)
                .mapToInt(cell -> 1)
                .sum();
    }

    private boolean isNeighbor(Position position, Position position2) {
        int lowerLimit = 0;
        int upperLimit = 4;

        return lowerLimit < squareDistanceOfTwoPositions(position, position2) &&
                squareDistanceOfTwoPositions(position, position2) < upperLimit;
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

        public GameOfLifeBuilder(MatrixSize matrixSize) {
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
            cellMatrix[position.row()][position.column()].setAlive(true);
            lastCellMatrix[position.row()][position.column()].setAlive(true);
            return this;
        }

        public GameOfLife build() {
            return new GameOfLife(cellMatrix, lastCellMatrix);
        }

    }
}
