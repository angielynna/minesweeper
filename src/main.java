
import java.io.*;
import java.util.*;
        public class main {
            public static void main(final String[] theArgs) throws FileNotFoundException {
                Scanner scanner = new Scanner(System.in);
                int numFields = 0;
                if (theArgs.length == 1) {
                    try {
                        scanner = new Scanner(new File(theArgs[0]));
                        int row = scanner.nextInt();
                        int col = scanner.nextInt();
                        mineSweeper(row, col, numFields, scanner);
                    } catch (FileNotFoundException e) {
                        throw new FileNotFoundException("File not found" + e);
                    }
                }
                scanner.close();
            }

            private static void mineSweeper(final int theRow, final int theCol, final int theNumFields, final Scanner theScanner) {
                int numFields = theNumFields;
                int row = theRow;
                int col = theCol;
                while (!(row == 0 || col == 0)) {
                    numFields++;
                    System.out.println("Field #" + numFields + ":");
                    char[][] field = new char[row + 1][col + 1];
                    for (int i = 0; i < row + 1; i++) {
                        char[] line = theScanner.nextLine().toCharArray();
                        for (int j = 0; j < col; j++) {
                            if (j > 0 && i > 0) {
                                field[i][j] = line[j];
                            }
                        }
                    }
                    char[][] filledMineField = fillField(field);
                    displayOut(filledMineField);
                    row = theScanner.nextInt();
                    col = theScanner.nextInt();

                }
            }

            private static void displayOut(final char[][] theFilledMine) {
                for (char[] chars : theFilledMine) {
                    for (int j = 0; j < theFilledMine[0].length; j++) {
                        System.out.print(chars[j]);
                    }
                    System.out.println();
                }
            }

            private static char[][] fillField(final char[][] theField) {
                int row = theField.length;
                int col = theField[0].length;
                char[][] mineFields = new char[row][col];
                for(int i = 0; i < row; i++){
                    for(int j = 0; j < col; j++) {
                        if(theField[i][j] == '*') {
                            mineFields[i][j] = '*';
                        } else {
                            mineFields[i][j] = adjacent(theField, row, col);
                        }
                    }
                }

                return mineFields;
            }

            private static char adjacent(final char[][] theField, final int theRow, final int theCol) {
                int myCount = 0;
                if(validValue(theField, theRow - 1, theCol - 1) &&
                        (theField[theRow - 1][theCol - 1] == '*')) {
                    myCount++;
                }
                if(validValue(theField, theRow - 1, theCol)) {
                    if(theField[theRow - 1][theCol] == '*') {
                        myCount++;
                    }
                }
                if(validValue(theField, theRow - 1, theCol + 1)) {
                    if(theField[theRow - 1][theCol + 1] == '*') {
                        myCount++;
                    }
                }
                //current row:
                if(validValue(theField, theRow, theCol - 1)) {
                    if(theField[theRow][theCol - 1] == '*') {
                        myCount++;
                    }
                }
                if(validValue(theField, theRow, theCol + 1)) {
                    if(theField[theRow][theCol + 1] == '*') {
                        myCount++;
                    }
                }
                //row below:
                if(validValue(theField, theRow + 1, theCol - 1)) {
                    if(theField[theRow + 1][theCol - 1] == '*') {
                        myCount++;
                    }
                }
                if(validValue(theField, theRow + 1, theCol)) {
                    if(theField[theRow + 1][theCol] == '*') {
                        myCount++;
                    }
                }
                if(validValue(theField, theRow + 1, theCol + 1)) {
                    if(theField[theRow + 1][theCol + 1] == '*') {
                        myCount++;
                    }
                }
                //System.out.println(myCount);
                return (char)(myCount + '0');

            }

            private static boolean validValue (final char[][] theMineField, final int theRow, final int theCol) {
                return theRow < theMineField.length && theRow >= 0 && theCol < theMineField[0].length && theCol >= 0;
            }
        }

///**
// * TCSS 360
// * Instructor: Tom Capaul
// * Assignment 1 - main.java
// */
//
//import java.io.*;
//import java.util.*;
//
///**
// * Displays minesweeper minefield with all adjacency values and bombs
// *
// * @author Angelynna Pyeatt
// * @version October 9, 2022
// */
//public class main {
//    /**
//     * Main method
//     * Takes in a file name from the console as program arguments and displays
//     * the correct minefield
//     *
//     * @param theArgs
//     * @throws Exception
//     */
//    public static void main(final String[] theArgs) throws FileNotFoundException {
//        Scanner sc = new Scanner(System.in);
//        int numFields = 0;
//        if(theArgs.length == 1) {   //reads from theArgs
//            try {
//                //open the file
//                sc = new Scanner(new File(theArgs[0]));
//                int rows = sc.nextInt();
//                int cols = sc.nextInt();
//
//                while (rows != 0 || cols != 0) {
//                    numFields++;
//                    System.out.println("Field #" + numFields + ":");
//                    //fill minefield:
//                    char[][] mineField = new char[rows + 1][cols + 1];
//                    for (int i = 0; i < rows; i++) {
//                        char[] line = sc.next().toCharArray();
//                        for (int j = 0; j < cols; j++) {
//                            if(j > 0 && i > 0) {
//                                mineField[i][j] = line[j];
//                            }
//                        }
//                    }
//                    char[][] generatedNumField = generateNums(mineField);
//
//                    //display correct output
//                    for(int i = 0; i < generatedNumField.length; i++) {
//                        for (int j = 0; j < generatedNumField[0].length; j++) {
//                            System.out.print(generatedNumField[i][j]);
//                        }
//                        System.out.println();
//                    }
//
//                    rows = sc.nextInt();
//                    cols = sc.nextInt();
//                }
//            } catch (FileNotFoundException e) {
//                throw new FileNotFoundException("File not found " + e);
//            }
//        }
//        sc.close();
//
//    }
//
//    /**
//     * this method fills a character array with the adjacency values for the
//     * minefield
//     *
//     * @param theMineField
//     * @return numFields
//     */
//    private static char[][] generateNums(final char[][] theMineField) {
//        char[][] numFields = new
//                char[theMineField.length][theMineField[0].length];
//        for(int i = 0; i < theMineField.length; i++) {
//            for(int j = 0; j < theMineField[0].length; j++) {
//                if(theMineField[i][j] == '*') {     //bomb
//                    numFields[i][j] = '*';
//                } else { //safe space
//                    numFields[i][j] = getAdjacencyValue(theMineField, i, j);
//                }
//            }
//        }
//        return numFields;
//    }
//
//    /**
//     * method calculates the adjacency values for a given position in
//     * the minefield
//     *
//     * @param theMineField
//     * @param theRow
//     * @param theCol
//     * @return (char) (value + '0')
//     */
//    private static char getAdjacencyValue(final char[][] theMineField,
//                                          int theRow, int theCol) {
//        int value = 0;      //adjacency
//
//        //Process of verification:
//        //1. If the position to be checked is within the range of the minefield
//        //2. If positions directly adjacent are a bomb
//
//        //check row above:
//        if(inRange(theRow - 1, theCol - 1, theMineField)) {
//            if(theMineField[theRow - 1][theCol - 1] == '*') {
//                value++;
//            }
//        }
//        if(inRange(theRow - 1, theCol, theMineField)) {
//            if(theMineField[theRow - 1][theCol] == '*') {
//                value++;
//            }
//        }
//        if(inRange(theRow - 1, theCol + 1, theMineField)) {
//            if(theMineField[theRow - 1][theCol + 1] == '*') {
//                value++;
//            }
//        }
//
//        //current row:
//        if(inRange(theRow, theCol - 1, theMineField)) {
//            if(theMineField[theRow][theCol - 1] == '*') {
//                value++;
//            }
//        }
//        if(inRange(theRow, theCol + 1, theMineField)) {
//            if(theMineField[theRow][theCol + 1] == '*') {
//                value++;
//            }
//        }
//
//        //row below:
//        if(inRange(theRow + 1, theCol - 1, theMineField)) {
//            if(theMineField[theRow + 1][theCol - 1] == '*') {
//                value++;
//            }
//        }
//        if(inRange(theRow + 1, theCol, theMineField)) {
//            if(theMineField[theRow + 1][theCol] == '*') {
//                value++;
//            }
//        }
//        if(inRange(theRow + 1, theCol + 1, theMineField)) {
//            if(theMineField[theRow + 1][theCol + 1] == '*') {
//                value++;
//            }
//        }
//        return (char) (value + '0');
//    }
//
//    /**
//     * Determines whether the row and column position set in the parameter
//     * the minefield are legal parameters
//     *
//     * @param theRow
//     * @param theCol
//     * @param theMaze
//     * @return boolean
//     */
//    private static boolean inRange(int theRow, int theCol,
//                                   char[][] theMaze) {
//        if(theRow < theMaze.length && theRow >= 0 &&
//                theCol < theMaze[0].length && theCol >= 0) {
//            return true;
//        }
//        return false;
//    }
//}