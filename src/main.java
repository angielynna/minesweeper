import java.io.*;
import java.util.*;

public class main {
    public static void main(final String[] theArgs) throws
            FileNotFoundException {

        PrintStream output = new PrintStream("output.txt");
        Scanner sc = null;
        StringBuilder sb = new StringBuilder();
        try {
            sc = new Scanner(new File(theArgs[0]));
            while(sc.nextLine() != "0 0") {
                int rows = sc.nextInt();
                int columns = sc.nextInt();

                //fill minefield:
                char[][] mineField = new char[rows][columns];
                for(int i = 0; i < rows; i++) {
                    for(int j = 0; j < columns; j++) {
                        if(sc.next() == "*") {
                            mineField[i][j] = '*';
                        } else if (sc.next() == ".") {
                            mineField[i][j] = '*';
                        }
                    }
                }

                //start adjacency shit:
                char[][] generatedNumField = generateNums(mineField);
            }
        } catch(FileNotFoundException e) {
            throw new FileNotFoundException("File not found " + e);
        }

    }

    private static char[][] generateNums(final char[][] theMineField) {
        char[][] numFields = new
                char[theMineField.length][theMineField[0].length];
        for(int i = 0; i < theMineField.length; i++) {
            for(int j = 0; j < theMineField[0].length; j++) {
                if(theMineField[i][j] == '*') {     //bomb
                    numFields[i][j] = '*';
                } else if (theMineField[i][j] == '.') { //safe space
                    numFields[i][j] = getAdjacencyValue(theMineField, i, j);
                }
            }
        }
        return numFields;
    }

    private static char getAdjacencyValue(final char[][] theMineField,
                                          int theRow, int theCol) {
        int value = 0;

        //check row above:
        //above and left
        if(theMineField[theRow - 1][theCol - 1] != '\0' &&
                theMineField[theRow - 1][theCol - 1] == '*') {
            value++;
        }
        //directly above
        if(theMineField[theRow - 1][theCol] != '\0' &&
                theMineField[theRow - 1][theCol] == '*') {
            value++;
        }
        //above and right
        if(theMineField[theRow - 1][theCol + 1] != '\0' &&
                theMineField[theRow - 1][theCol + 1] == '*') {
            value++;
        }

        //check current row
        //left
        if(theMineField[theRow][theCol - 1] != '\0' &&
                theMineField[theRow][theCol - 1] == '*') {
            value++;
        }
        //right
        if(theMineField[theRow][theCol + 1] != '\0' &&
                theMineField[theRow][theCol + 1] == '*') {
            value++;
        }

        //check row below
        //below and left
        if(theMineField[theRow + 1][theCol - 1] != '\0' &&
                theMineField[theRow + 1][theCol - 1] == '*') {
            value++;
        }
        //directly below
        if(theMineField[theRow + 1][theCol] != '\0' &&
                theMineField[theRow + 1][theCol] == '*') {
            value++;
        }
        //below and right
        if(theMineField[theRow + 1][theCol + 1] != '\0' &&
                theMineField[theRow + 1][theCol + 1] == '*') {
            value++;
        }
        return (char) value;
    }

}
