package FileManagerPackage;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static void readFile(Scanner s, ArrayList<Task> list) {
        int currentIteration = 0;
        while (s.hasNext()) {
            String[] currentLine = s.nextLine().split("\\s*\\|\\s*");
            if (currentLine[0].equals("D")) {
                list.add(new Deadline(currentLine[2], currentLine[3]));
            } else if (currentLine[0].equals("T")) {
                list.add(new Todo(currentLine[2], currentLine[2]));
            } else if (currentLine[0].equals("E")) {
                String[] duration = currentLine[3].split("\\s*\\-\\s*");
                list.add(new Event(currentLine[2],
                        "from: " + duration[0] + " to: " + duration[1]));
            }

            if (currentLine[1].equals("1")) {
                list.get(currentIteration).setDone(true);
            }
            currentIteration++;
        }
    }

    private static void checkIfDirExist(File f, String filePath) {
        if (!f.exists()) {
            File file = new File(filePath);
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            try {
                FileWriter fileWriter = new FileWriter(filePath);
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("     An error occurred while creating file: " + e.getMessage());
            }
        }
    }

    public static File getFile() {
        String filePath = Paths.get(System.getProperty("user.dir"), "data", "ThawBot.txt").toString();
        File f = new File(filePath);
        checkIfDirExist(f, filePath);
        return f;
    }

    public static void saveData(ArrayList<Task> list) {
        try {
            final String filePath = "data/ThawBot.txt";
            FileWriter fw = new FileWriter(filePath);
            String textToAdd = "";
            for (int i = 0; i < list.size(); i++) {
                textToAdd += list.get(i).printFileFormat() + System.lineSeparator();
            }
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println("     An error occurred while reading file: " + e.getMessage());
        }
    }
}
