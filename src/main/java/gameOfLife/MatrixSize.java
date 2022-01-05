package gameOfLife;

import static gameOfLife.Validate.greaterThan;

public class MatrixSize {
    private final int rows;
    private final int columns;

    public MatrixSize(int rows, int columns) {
        this.rows = greaterThan(rows, 1);
        this.columns = greaterThan(columns, 1);
    }

    public int rows() {
        return rows;
    }

    public int columns() {
        return columns;
    }
}
