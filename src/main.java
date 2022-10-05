import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] theArgs) throws
            FileNotFoundException {
        File output = new File("output.txt");

        Scanner sc = null;
        StringBuilder sb = new StringBuilder();

        try{
            sc = new Scanner(new File(theArgs[0]));
            while(sc.hasNext()) {
                int rows = sc.nextInt();
                int columns = sc.nextInt();
                char[][] mineField = new char[rows][columns];
                for(int i = 0; i < rows; i++) {
                    for(int j = 0; j < columns; j++) {

                    }
                }
            }
        } catch(FileNotFoundException e) {
            throw new FileNotFoundException("File not found " + e);
        }
    }

}
