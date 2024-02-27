package ReadWriteToFile;

import Tasking.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReadWriteFile {

    public static final String DIRECTORY_PATH = "./data/";
    public static final String FILE_PATH = DIRECTORY_PATH + "/davvy.txt";

    public static void createFile() throws IOException {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdir(); // Not a must to assign its return value
        }
        File file = new File(FILE_PATH);
        if (!file.exists()){
            file.createNewFile(); // Not a must to assign its return value
        }
    }
    public static void writeToFile(String textToAppend) throws IOException {
        createFile();
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write(textToAppend + "\n");
        fw.close();
    }

    public static void rewriteFile() throws IOException {
        File file = new File(FILE_PATH);
        file.delete();
        file.createNewFile();
        for (int i = 0; i < TaskList.listLength(); i++) {
            Davvy.writeData(TaskList.getTask(i));
        }
    }

    public static void readFile() throws IOException {
        try {
            createFile();
            File file = new File(FILE_PATH);
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                // Data is split into type, status, description and time (into 4/5 parts)
                String[] data = s.nextLine().split("\\|");
                Task task = null;
                switch (data[0].trim()) {
                case "T":
                    task = new Todo(" " + data[2].trim());
                    break;
                case "D":
                    task = new Deadline(" " + data[2].trim() + " ",
                            " " + data[3].trim() + " ");
                    break;
                case "E":
                    task = new Events(" " + data[2].trim(),
                            " " + data[3].trim() + " ", " " + data[4].trim());
                    break;
                default:
                    System.out.println("Unknown Data type found in file");
                    break;
                }
                if (!(task == null)) {
                    // Only executed if a valid task is obtained from file
                    TaskList.addTask(task, true);
                    if (data[1].contains("1")) {
                        task.markDone(false);
                    } else {
                        task.markNotDone(false);
                    }
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("Exception thrown: " + e.getMessage());
        }
    }
}
