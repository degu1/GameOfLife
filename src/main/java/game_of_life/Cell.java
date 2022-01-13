package game_of_life;

import java.util.Objects;

import static game_of_life.Validate.isNotStrictlyBetween;

public class Cell {

    private final Position position;
    private boolean alive;
    private boolean aliveLastRender;

    public Cell(Position position) {
        this.position = position;
        this.alive = false;
        this.aliveLastRender = false;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void renderCell(int numberOfNeighbours) {
        if (alive) {
            renderAliveCell(numberOfNeighbours);
        } else {
            renderDeadCell(numberOfNeighbours);
        }
    }


    public Position position() {
        return position;
    }

    public boolean isAlive() {
        return alive;
    }

    private void renderAliveCell(int numberOfNeighbours) {
        if (isNotStrictlyBetween(numberOfNeighbours, 2, 3)) {
            alive = false;
        }
    }

    private void renderDeadCell(int numberOfNeighbours) {
        if (numberOfNeighbours == 3) {
            alive = true;
        }
    }

    public void updateAliveLastRender() {
        aliveLastRender = alive;
    }

    public boolean isAliveLastRender() {
        return aliveLastRender;
    }

    public void setAliveLastRender(boolean alive) {
        this.aliveLastRender = alive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return alive == cell.alive && Objects.equals(position, cell.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, alive);
    }


}
