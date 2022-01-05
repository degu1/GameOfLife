package gameOfLife;

public class Validate {

    public static int greaterThan(int value, int limit) {
        if (value <= limit) {
            throw new IllegalArgumentException();
        }
        return value;
    }

    public static boolean isBetween(double value, int lowerLimit, int upperLimit) {
        if (value <= lowerLimit || value >= upperLimit) {
            return false;
        }
        return true;
    }

    public static boolean isNotStrictlyBetween(double value, int lowerLimit, int upperLimit) {
        if (lowerLimit <= value && value <= upperLimit) {
            return false;
        }
        return true;
    }

    public static <T> T notNull(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        return t;
    }

    public static void validPosition(Position position, MatrixSize matrixSize) {
        notNull(position);
        if (isNotStrictlyBetween(position.row(), 0, matrixSize.rows())) {
            throw new IllegalArgumentException();
        }
        if (isNotStrictlyBetween(position.column(), 0, matrixSize.columns())) {
            throw new IllegalArgumentException();
        }
    }


}
