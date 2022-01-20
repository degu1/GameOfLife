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

    public Cell[][] cellMatrix() {
        return cellMatrix;
    }

    public MatrixSize matrixSize() {
        return matrixSize;
    }

    public Cell cell(Position position) {
        return cellMatrix[position.row()][position.column()];
    }

    public void renderNextGeneration() {
        renderNextGenerationForAllCells();
        updateLastCellMatrix();
    }

    private void renderNextGenerationForAllCells() {
        Arrays.stream(cellMatrix).flatMap(Arrays::stream).parallel()
                .forEach(cell -> cell.renderNextGeneration(numberOfNeighbors(cell)));
    }

    private int numberOfNeighbors(Cell currentCell) {
        return neighbors(currentCell).stream()
                .filter(cell -> notEqualPositions(currentCell, cell))
                .filter(Cell::isAliveLastGeneration)
                .mapToInt(cell -> 1)
                .sum();
    }

    private List<Cell> neighbors(Cell currentCell) {
        Position position = currentCell.position();
        List<Cell> neighbors = new ArrayList<>();
        for (int row = previousIndex(position.row()); row <= followingIndex(position.row()); row++) {
            for (int col = previousIndex(position.column()); col <= followingIndex(position.column()); col++) {
                if (isBetween(row, -1, matrixSize.rows()) && isBetween(col, -1, matrixSize.columns())) {
                    neighbors.add(cellMatrix[row][col]);
                }
            }
        }
        return neighbors;
    }

    private int previousIndex(int index) {
        return index - 1;
    }

    private int followingIndex(int index) {
        return index + 1;
    }

    private boolean notEqualPositions(Cell currentCell, Cell cell) {
        return !currentCell.position().equals(cell.position());
    }

    private void updateLastCellMatrix() {
        Arrays.stream(cellMatrix).flatMap(Arrays::stream)
                .forEach(Cell::updateAliveLastGeneration);
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
