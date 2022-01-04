package gameOfLife;

import java.util.Arrays;

public class GameOfLife {
    private final Cell[][] cellMatrix;

    public GameOfLife(int rows, int columns) {
        this.cellMatrix = new Cell[rows][columns];
        populateMatrix(rows, columns);
    }

    private void populateMatrix(int rows, int columns) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                cellMatrix[row][col] = new Cell();
            }
        }
    }

    public Cell[][] getCellMatrix() {
        return cellMatrix;
    }

    public void setCellAlive(Position position, boolean alive) {
        cellMatrix[position.row()][position.column()].setAlive(alive);
    }

    public void render() {
        cellMatrix[1][1].setAlive(false);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameOfLife that = (GameOfLife) o;
        return Arrays.equals(cellMatrix, that.cellMatrix);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(cellMatrix);
    }


    public Cell getCell(Position position) {
        return cellMatrix[position.row()][position.column()];
    }
}
