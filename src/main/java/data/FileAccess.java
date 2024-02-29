package data;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class FileAccess {

    public static void readFromFile (String filePath) throws IOException 
    {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        sc.close();
    }

    public static void appendToFile (String filePath, String textToAppend) throws IOException {

        FileWriter fw = new FileWriter(filePath, true); 
        fw.write(textToAppend);
        fw.close();
    }
}