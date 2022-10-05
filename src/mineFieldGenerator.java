import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

public class mineFieldGenerator {
    public static void main(String[] theArgs) throws
            FileNotFoundException {
        Random rd = new Random();
        int rows = rd.nextInt(100) + 1;
        int columns = rd.nextInt(100) + 1;

        double bombSpawnRate = rd.nextDouble();
        int numBombs = 1;
        if ((int) (bombSpawnRate * (rows * columns)) > 1) {
            numBombs = (int) (bombSpawnRate * (rows * columns));
        }
        generateLocations(rows, columns, numBombs);
    }

    public static void generateLocations(int theRows, int theCols,
                                          int theBombs) throws
                                        FileNotFoundException{
        Random rand = new Random();
        char[][] field = new char[theRows][theCols];
        StringBuilder sb = new StringBuilder();
        sb.append(theRows + " " + theCols + "\n");

        //generate bomb locations
        int[][] bombs = genBombLocation(theBombs, theRows, theCols);

        //filling field with bombs
        for(int i = 0; i < bombs.length; i++) {
            for(int j = 0; j < 2; j++) {
                field[bombs[i][0]][bombs[i][1]] = '*';
            }
        }

        //filling the rest of the field:
        for(int i = 0; i < theRows; i++){
            for (int j = 0; j < theCols; j++) {
                if(field[i][j] != '*') {
                    field[i][j] = '.';
                }
            }
        }

        output(field, sb);
    }

    private static int[][] genBombLocation(int theBombs, int theRows,
                                           int theColumns) {
        int[][] locations = new int[theBombs][2];
        Random rd = new Random();
        for(int i = 0; i < theBombs; i++) {
            int bombRow = rd.nextInt(theRows);
            for(int j = 0; j < 1; j++) {
                int bombCol = rd.nextInt(theColumns);
                locations[i][j] = bombRow;
                locations[i][j + 1] = bombCol;
            }
        }
        return locations;
    }

    private static void output(char[][] theField,
                               StringBuilder theString)
            throws FileNotFoundException {
        try {
            PrintStream output = new PrintStream("generated.txt");
            for(int i = 0; i < theField.length; i++) {
                for(int j = 0; j < theField[0].length; j++) {
                    theString.append(theField[i][j]);
                }
                theString.append("\n");
            }
            output.append(theString.toString());
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Cannot find file " + e);
        }
    }
}