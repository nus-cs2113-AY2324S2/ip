package oley.commands;

import oley.tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Represents methods that are used to make changes to the data file.
 * It deals with writing to and deleting from data file, as well as load data file during initialisation.
 */
public class Storage {
    /**
     * Append a new task to the data file.
     *
     * @param filePath Path to the file that contains the data.
     * @param textToAppend Text that are adding to the end of the data file.
     * @throws IOException If fail to write to the data file.
     */
    public static void appendToFile (String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }

    /**
     * Re-write the tasks in the task list after some changes, e.g. delete, to the data file.
     *
     * @param filePath Path to the file that contains the data.
     * @param tasks Task list that contains existing tasks.
     * @throws IOException If fail to change the data file.
     */
    public static void changeFile (String filePath, TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(tasks.get(0).format() + System.lineSeparator());
        fw.close();
        for (int i = 1; i < tasks.size(); i++) {
            appendToFile(filePath, tasks.get(i).format());
        }
    }

    /**
     * Clear all data in the data file by creating a new file in the same path.
     *
     * @param filePath Path to the file that contains the data.
     * @throws IOException If fail to clear the data file.
     */
    public static void clearFile (String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.close();
    }

    /**
     * Load tasks from the data file when initialising the chatbot.
     *
     * @param tasks Task list that contains existing tasks.
     * @param file Path to the file that contains the data.
     */
    public static void load(TaskList tasks, String file) {
        Scanner s;
        try {
            File f = new File(file);
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            Ui.printFileNotFound();
            Ui.lineBreaker();
            Path folder = Paths.get("./data/");
            Path filePath = Paths.get(file);
            try {
                Files.createDirectory(folder);
                Files.createFile(filePath);
            } catch (IOException ex) {
                Ui.printFileNotCreated();
            }
            return;
        }

        while (s.hasNext()) {
            String line = s.nextLine();
            String isDone = line.substring(0,1);
            String task = line.substring(1);
            try {
                Command.addTask(tasks, task, true);
            } catch (InputNotRecognizedException e) {
                Ui.printFileCorrupted();
            }
            if (isDone.equals("1")) {
                Command.mark(tasks, "mark " + tasks.size(), true);
            }
        }
    }
}
