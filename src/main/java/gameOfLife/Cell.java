package gameOfLife;

import java.util.Objects;

public class Cell {

    private final Position positon;
    private boolean alive;

    public Cell(Position positon) {
        this.positon = positon;
        this.alive = false;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Position position() {
        return positon;
    }

    public boolean isAlive() {
        return alive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return alive == cell.alive && Objects.equals(positon, cell.positon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positon, alive);
    }


}
