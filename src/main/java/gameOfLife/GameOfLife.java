package gameOfLife;

public class GameOfLife {
    Cell[][] cellMatrix;

    public GameOfLife(int rows, int columns) {
        this.cellMatrix = new Cell[rows][columns];
    }

    public Cell[][] getCellMatrix() {
        return cellMatrix;
    }
}
