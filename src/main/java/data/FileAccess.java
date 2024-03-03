package data;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles the file access
 * @see FileAccess
 * 
 * @author yyangdaa
 * @version 0.1
 * @since 2024-03-03
 * 
 */
public class FileAccess {

    /**
     * Reads the file from the file path
     * @param filePath the file path
     * @throws IOException if an error occurs while reading from file
     */

    public static void readFromFile (String filePath) throws IOException 
    {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        sc.close();
    }

    /**
     * Appends the text to the file
     * @param filePath the file path
     * @param textToAppend the text to append
     * @throws IOException if an error occurs while writing to file
     */

    public static void appendToFile (String filePath, String textToAppend) throws IOException {

        FileWriter fw = new FileWriter(filePath, true); 
        fw.write(textToAppend);
        fw.close();
    }
}