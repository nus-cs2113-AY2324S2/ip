package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    static File f = new File("data.txt");
     public static ArrayList<String> loadData() {
        ArrayList<String> listString = new ArrayList<>();

        //try to create a file if it does not exist
        try {
            if (!f.exists()) {
                boolean created = f.createNewFile();
                if (created) {
                    System.out.println("File created successfully.");
                } else {
                    System.out.println("Failed to create the file.");
                }
            }

            //take in all the lines in string data type
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                listString.add(s.nextLine());
            }
            return listString;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //save the list in our file as string Data type
    public static void saveData(ArrayList<String> listString) {
        try (FileWriter fw = new FileWriter(f)) {
            for (String task : listString) {
                fw.write(task + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
