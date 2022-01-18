package game_of_life;

import java.util.Objects;

import static game_of_life.Validate.isNotStrictlyBetween;

public class Cell {

    private final Position position;
    private boolean alive;
    private boolean aliveLastGeneration;

    public Cell(Position position) {
        this.position = position;
        this.alive = false;
        this.aliveLastGeneration = false;
    }

    public Position position() {
        return position;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAliveLastGeneration() {
        return aliveLastGeneration;
    }

    public void setAliveLastGeneration(boolean alive) {
        this.aliveLastGeneration = alive;
    }

    public void renderNextGeneration(int numberOfNeighbours) {
        if (alive) {
            renderNextGenerationIfCellIsAlive(numberOfNeighbours);
        } else {
            renderNextGenerationIfCellIsDead(numberOfNeighbours);
        }
    }

    private void renderNextGenerationIfCellIsAlive(int numberOfNeighbours) {
        if (isNotStrictlyBetween(numberOfNeighbours, 2, 3)) {
            alive = false;
        }
    }

    private void renderNextGenerationIfCellIsDead(int numberOfNeighbours) {
        if (numberOfNeighbours == 3) {
            alive = true;
        }
    }

    public void updateAliveLastGeneration() {
        aliveLastGeneration = alive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return alive == cell.alive && Objects.equals(position, cell.position);
    }
}
