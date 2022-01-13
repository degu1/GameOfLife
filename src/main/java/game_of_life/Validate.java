package game_of_life;

public class Validate {

    private Validate() {
        throw new IllegalStateException("Utility class");
    }

    public static int greaterThan(int value, int limit) {
        if (value <= limit) {
            throw new IllegalArgumentException();
        }
        return value;
    }

    public static boolean isBetween(double value, int lowerLimit, int upperLimit) {
        return lowerLimit < value && value < upperLimit;
    }

    public static boolean isNotStrictlyBetween(double value, int lowerLimit, int upperLimit) {
        return !(lowerLimit <= value && value <= upperLimit);
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
