package game_of_life;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static game_of_life.Validate.isBetween;

public class Board {
    private final Cell[][] cellMatrix;
    private final MatrixSize matrixSize;

    public Board(MatrixSize matrixSize) {
        this.matrixSize = matrixSize;
        this.cellMatrix = new Cell[matrixSize.rows()][matrixSize.columns()];
        populateMatrix(matrixSize);
    }

    private void populateMatrix(MatrixSize matrixSize) {
        for (int row = 0; row < matrixSize.rows(); row++) {
            for (int col = 0; col < matrixSize.columns(); col++) {
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
        Arrays.stream(cellMatrix).flatMap(Arrays::stream).parallel()
                .forEach(cell -> cell.renderCell(numberOfNeighbors(cell)));
    }

    private void updateLastCellMatrix() {
        Arrays.stream(cellMatrix).flatMap(Arrays::stream)
                .forEach(Cell::updateAliveLastRender);
    }

    private int numberOfNeighbors(Cell currentCell) {
        return neighbors(currentCell).stream()
                .filter(cell -> notEqualPositions(currentCell, cell))
                .filter(Cell::isAliveLastRender)
                .mapToInt(cell -> 1)
                .sum();
    }

    private boolean notEqualPositions(Cell currentCell, Cell cell) {
        return !currentCell.position().equals(cell.position());
    }

    private List<Cell> neighbors(Cell currentCell) {
        List<Cell> neighbors = new ArrayList<>();
        for (int row = currentCell.position().row() - 1; row <= currentCell.position().row() + 1; row++) {
            for (int col = currentCell.position().column() - 1; col <= currentCell.position().column() + 1; col++) {
                if (isBetween(row, -1, matrixSize.rows()) && isBetween(col, -1, matrixSize.columns())) {
                    neighbors.add(cellMatrix[row][col]);
                }
            }
        }
        return neighbors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board that = (Board) o;
        for (int i = 0; i < cellMatrix.length; i++) {
            if (!Arrays.equals(cellMatrix[i], that.cellMatrix[i])) {
                return false;
            }
        }
        return true;
    }
}
