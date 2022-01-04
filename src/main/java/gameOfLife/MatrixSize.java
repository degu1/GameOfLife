package gameOfLife;

public class MatrixSize {
    private final int rows;
    private final int columns;

    public MatrixSize(int rows, int columns) {
        if (rows < 1) {
            throw new IllegalArgumentException();
        }
        if (columns < 1) {
            throw new IllegalArgumentException();
        }
        this.rows = rows;
        this.columns = columns;
    }

    public int rows() {
        return rows;
    }

    public int columns() {
        return columns;
    }
}
