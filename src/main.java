import java.io.*;
import java.util.*;

//testing

public class main {
    public static void main(final String[] theArgs) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        int numFields = 0;
        if(theArgs.length == 1) {
            try {
                sc = new Scanner(new File(theArgs[0]));
                int rows = sc.nextInt();
                int cols = sc.nextInt();

                while (rows != 0 || cols != 0) {
                    numFields++;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Field #" + numFields + ":\n");
                    System.out.println("Field #" + numFields + ":");
                    //fill minefield:
                    char[][] mineField = new char[rows + 1][cols + 1];

                    for (int i = 0; i < rows; i++) {
                        char[] line = sc.next().toCharArray();
                        for (int j = 0; j < cols; j++) {
                            if(j > 0 && i > 0) {
                                mineField[i][j] = line[j];
                            }
                        }
                    }

                    char[][] generatedNumField = generateNums(mineField);

                    for(int i = 0; i < generatedNumField.length; i++) {
                        for (int j = 0; j < generatedNumField[0].length; j++) {
                            System.out.print(generatedNumField[i][j]);
                            sb.append(generatedNumField[i][j]);
                        }
                        System.out.println();
                        sb.append("\n");
                    }
                    displayMineField(sb, numFields);

                    rows = sc.nextInt();
                    cols = sc.nextInt();
                }
            } catch (FileNotFoundException e) {
                throw new FileNotFoundException("File not found " + e);
            }
        }
        sc.close();

    }

    private static char[][] generateNums(final char[][] theMineField) {
        char[][] numFields = new
                char[theMineField.length][theMineField[0].length];
        for(int i = 0; i < theMineField.length; i++) {
            for(int j = 0; j < theMineField[0].length; j++) {
                if(theMineField[i][j] == '*') {     //bomb
                    numFields[i][j] = '*';
                } else { //safe space
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
        if(inRange(theRow - 1, theCol - 1, theMineField)) {
            if(theMineField[theRow - 1][theCol - 1] == '*') {
                value++;
            }
        }
        if(inRange(theRow - 1, theCol, theMineField)) {
            if(theMineField[theRow - 1][theCol] == '*') {
                value++;
            }
        }
        if(inRange(theRow - 1, theCol + 1, theMineField)) {
            if(theMineField[theRow - 1][theCol + 1] == '*') {
                value++;
            }
        }

        //current row:
        if(inRange(theRow, theCol - 1, theMineField)) {
            if(theMineField[theRow][theCol - 1] == '*') {
                value++;
            }
        }
        if(inRange(theRow, theCol + 1, theMineField)) {
            if(theMineField[theRow][theCol + 1] == '*') {
                value++;
            }
        }

        //row below:
        if(inRange(theRow + 1, theCol - 1, theMineField)) {
            if(theMineField[theRow + 1][theCol - 1] == '*') {
                value++;
            }
        }
        if(inRange(theRow + 1, theCol, theMineField)) {
            if(theMineField[theRow + 1][theCol] == '*') {
                value++;
            }
        }
        if(inRange(theRow + 1, theCol + 1, theMineField)) {
            if(theMineField[theRow + 1][theCol + 1] == '*') {
                value++;
            }
        }
        return (char) (value + '0');
    }

    private static void displayMineField(final StringBuilder theField,
                                         final int theNumFields) {
        PrintStream ps = null;
        try {
            ps = new PrintStream(new File("output.txt"));
            ps.append(theField.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ps.close();
    }

    private static boolean inRange(int theRow, int theCol,
                                   char[][] theMaze) {
        if(theRow < theMaze.length && theRow >= 0 &&
                theCol < theMaze[0].length && theCol >= 0) {
            return true;
        }
        return false;
    }
}