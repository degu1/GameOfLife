package gameOfLife;

import java.util.Arrays;
import java.util.Objects;

import static gameOfLife.Validate.isBetween;
import static java.lang.Math.pow;

public class Board {
    private final Cell[][] cellMatrix;
    private final MatrixSize matrixSize;

    public Board(MatrixSize matrixSize) {
        this.matrixSize = matrixSize;
        this.cellMatrix = new Cell[matrixSize.rows()][matrixSize.columns()];
        populateMatrix(matrixSize.rows(), matrixSize.columns());
    }

    private void populateMatrix(int rows, int columns) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                cellMatrix[row][col] = new Cell(new Position(row, col));
            }
        }
    }

    public Cell cell(Position position) {
        return cellMatrix[position.row()][position.column()];
    }

    public Cell[][] cellMatrix() {
        return cellMatrix;
    }

    public MatrixSize matrixSize() {
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
        Arrays.stream(cellMatrix).flatMap(Arrays::stream)
                .forEach(Cell::updateAliveLastRender);
    }

    private int numberOfNeighbors(Cell currentCell) {
        return Arrays.stream(cellMatrix).flatMap(Arrays::stream)
                .filter(cell -> isNeighbor(currentCell.position(), cell.position()))
                .filter(Cell::isAliveLastRender)
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
        Board that = (Board) o;
        for (int i = 0; i < cellMatrix[0].length; i++) {
            if (!Arrays.equals(cellMatrix[i], that.cellMatrix[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(matrixSize);
        result = 31 * result + Arrays.hashCode(cellMatrix);
        return result;
    }
}
