import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class mineFieldGenerator {
    public static void main(String[] theArgs) throws
            FileNotFoundException {
        PrintStream ps = new PrintStream("generated.txt");

        Random rd = new Random();
        int rows = rd.nextInt(5) + 1;
        int columns = rd.nextInt(5) + 1;

        double bombSpawnRate = rd.nextDouble();
        int numBombs = 1;
        if ((int) (bombSpawnRate * (rows * columns)) >= 1) {
            numBombs = (int) (bombSpawnRate * (rows * columns));
        }
        generateLocations(rows, columns, numBombs, ps);
    }

    public static void generateLocations(int theRows, int theCols,
                                          int theBombs,
                                          PrintStream theOutput) {
        Random rand = new Random();
        StringBuilder field = new StringBuilder();
        int bombRow = rand.nextInt(theRows + 1) - 1;
        int bombCol = rand.nextInt(theCols + 1) - 1;
        while (theBombs > 0) {
            for (int i = 0; i < theRows; i++) {
                for (int j = 0; j < theCols; j++) {
                    if (i == bombRow && j == bombCol) {
                        field.append('*');
                        bombRow = rand.nextInt(theRows);
                        bombCol = rand.nextInt(theCols);
                    } else {
                        field.append('.');
                    }
                }
                field.append("\n");
            }
            theBombs--;
        }
        System.out.println(field);
    }
}