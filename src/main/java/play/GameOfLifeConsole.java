package play;

import static java.util.concurrent.TimeUnit.SECONDS;

import game_of_life.Position;
import game_of_life.MatrixSize;
import game_of_life.GameOfLife;

public class GameOfLifeConsole {

    public static void main(String[] args) {
        GameOfLife gameOfLife = new GameOfLife.GameOfLifeBuilder(new MatrixSize(10, 10))
                .cellAlive(new Position(0, 1))
                .cellAlive(new Position(1, 2))
                .cellAlive(new Position(2, 0))
                .cellAlive(new Position(2, 1))
                .cellAlive(new Position(2, 2))
                .build();

        for (int i = 0; i < 29; i++) {
            printGameMatrix(gameOfLife);
            gameOfLife.render();
            try {
                SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private static void printGameMatrix(GameOfLife gameOfLife) {
        int rows = gameOfLife.getCellMatrix().length;
        int columns = gameOfLife.getCellMatrix()[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (gameOfLife.getCellMatrix()[row][col].isAlive()) {
                    System.out.print('X');
                } else {
                    System.out.print('O');
                }
                if (col == columns - 1) {
                    System.out.println();
                }

            }
        }
        System.out.println();
    }

}
