import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
public class FileProcessor {

    public static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static void startUpCheck(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            System.out.println("The file already exists.");
        } else {
            try {
                // Create the file
                if (file.createNewFile()) {
                    System.out.println("File created successfully.");
                } else {
                    System.out.println("Failed to create the file.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
