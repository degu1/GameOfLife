package game_of_life;

import static game_of_life.Validate.greaterThan;

public record MatrixSize(int rows, int columns) {
    public MatrixSize(int rows, int columns) {
        this.rows = greaterThan(rows, 1);
        this.columns = greaterThan(columns, 1);
    }
}
