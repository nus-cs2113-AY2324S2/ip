package alpaca.file;

import alpaca.UI.ResponseManager;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileReader {

    private static void printFileContents(String filePath) throws IOException {
        File f = new File(filePath);

        if (!f.exists()) {
            if (f.createNewFile()) {
                System.out.println("No existing task list, file created: " + f.getName());
                ResponseManager.printLine();
            } else {
                System.out.println("Failed to create the file.");
                return;
            }
        }

        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void startFileReader() {
        try {
            printFileContents("data/Alpaca.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            ResponseManager.printLine();
        }
    }
}