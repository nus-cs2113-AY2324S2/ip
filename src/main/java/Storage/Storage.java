package Storage;

import Tasking.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    public static final String DIRECTORY_PATH = "./data/";
    public static final String FILE_PATH = DIRECTORY_PATH + "/davvy.txt";

    /**
     * Checks if both the directory and the file exists. If either or both do not, it will be
     * created
     *
     * @throws IOException if there was an issue creating the file
     */
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

    /**
     * Writes data to file
     *
     * @param textToAppend the string to be added into storage
     * @throws IOException if there was an issue writing into the file
     */
    public static void writeToFile(String textToAppend) throws IOException {
        createFile();
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write(textToAppend + "\n");
        fw.close();
    }

    /**
     * Completely re-writes all data onto a fresh file
     *
     * @throws IOException if there was an issue deleting/writing into the file
     */
    public static void rewriteFile() throws IOException {
        File file = new File(FILE_PATH);
        file.delete();
        file.createNewFile();
        for (int i = 0; i < TaskList.listLength(); i++) {
            Davvy.writeData(TaskList.getTask(i));
        }
    }

    /**
     * Uses a scanner to read data line by line from the input file, before breaking it down
     * and initialising the data into the program before the user is allowed to start interacting
     * with the bot
     *
     * @throws IOException if there is an issue reading data from the file
     */
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
                    task = new Todo(data[2].trim());
                    break;
                case "D":
                    task = new Deadline(data[2].trim() + " ",
                            " " + data[3].trim() + " ");
                    break;
                case "E":
                    task = new Events(data[2].trim(),
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
