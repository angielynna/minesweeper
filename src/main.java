import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Displays minesweeper minefield with all adjacency values and bombs.
 *
 * @author Angelynna Pyeatt
 * @version October 9, 2022
 */
class main {
    public static void main(final String[] theArgs)
            throws FileNotFoundException {
        final Scanner sc = new Scanner(System.in);
        int numFields = 0;
        int rows = sc.nextInt();
        int cols = sc.nextInt();

        while (rows != 0 || cols != 0) {
            numFields++;
            //fill minefield:
            final char[][] mineField = new char[rows][cols];
            for (int i = 0; i < rows; i++) {
                final char[] line = sc.next().toCharArray();
                for (int j = 0; j < cols; j++) {
                    if (j > 0 && i > 0) {
                        mineField[i][j] = line[j];
                    }
                }
            }
            final char[][] generatedNumField = generateNums(mineField);

            //display correct output to System.out
            final PrintStream console = new PrintStream(System.out);
            System.setOut(console);
            console.println("Field #" + numFields + ":");
            for (int i = 0; i < generatedNumField.length; i++) {
                for (int j = 0; j < generatedNumField[0].length; j++) {
                    console.print(generatedNumField[i][j]);
                }
                console.println();
            }

            rows = sc.nextInt();
            cols = sc.nextInt();
        }

    }

    /**
     * This method fills a character array with the adjacency values for the
     * minefield.
     *
     * @param theMineField
     * @return numFields
     */
    private static char[][] generateNums(final char[][] theMineField) {
        final char[][] numFields = new
                char[theMineField.length][theMineField[0].length];
        for (int i = 0; i < theMineField.length; i++) {
            for (int j = 0; j < theMineField[0].length; j++) {
                if (theMineField[i][j] == '*') {     //bomb
                    numFields[i][j] = '*';
                } else { //safe space
                    numFields[i][j] = getAdjacencyValue(theMineField, i, j);
                }
            }
        }
        return numFields;
    }

    /**
     * Method calculates the adjacency values for a given position in
     * the minefield.
     *
     * @param theMineField
     * @param theRow
     * @param theCol
     * @return (char) (value + '0')
     */
    private static char getAdjacencyValue(final char[][] theMineField,
                                          final int theRow,
                                          final int theCol) {
        int value = 0;      //adjacency

        //Process of verification:
        //1. If the position to be checked is within the range of the minefield
        //2. If positions directly adjacent are a bomb

        //check row above:
        if (inRange(theRow - 1, theCol - 1, theMineField)) {
            if (theMineField[theRow - 1][theCol - 1] == '*') {
                value++;
            }
        }
        if (inRange(theRow - 1, theCol, theMineField)) {
            if (theMineField[theRow - 1][theCol] == '*') {
                value++;
            }
        }
        if (inRange(theRow - 1, theCol + 1, theMineField)) {
            if (theMineField[theRow - 1][theCol + 1] == '*') {
                value++;
            }
        }

        //current row:
        if (inRange(theRow, theCol - 1, theMineField)) {
            if (theMineField[theRow][theCol - 1] == '*') {
                value++;
            }
        }
        if (inRange(theRow, theCol + 1, theMineField)) {
            if (theMineField[theRow][theCol + 1] == '*') {
                value++;
            }
        }

        //row below:
        if (inRange(theRow + 1, theCol - 1, theMineField)) {
            if (theMineField[theRow + 1][theCol - 1] == '*') {
                value++;
            }
        }
        if (inRange(theRow + 1, theCol, theMineField)) {
            if (theMineField[theRow + 1][theCol] == '*') {
                value++;
            }
        }
        if (inRange(theRow + 1, theCol + 1, theMineField)) {
            if (theMineField[theRow + 1][theCol + 1] == '*') {
                value++;
            }
        }
        return (char) (value + '0');
    }

    /**
     * Determines whether the row and column position set in the parameter
     * the minefield are legal parameters.
     *
     * @param theRow
     * @param theCol
     * @param theMaze
     * @return boolean
     */
    private static boolean inRange(final int theRow, final int theCol,
                                   final char[][] theMaze) {
        if (theRow < theMaze.length && theRow >= 0
                && theCol < theMaze[0].length && theCol >= 0) {
            return true;
        }
        return false;
    }
}
