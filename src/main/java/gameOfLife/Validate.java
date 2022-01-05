package gameOfLife;

public class Validate {

    public static int greaterThan(int value, int limit) {
        if (value <= limit) {
            throw new IllegalArgumentException();
        }
        return value;
    }

    public static <T> T notNull(T t) {
        if (t.equals(null)) {
            throw new NullPointerException();
        }
        return t;
    }
}
