package alpaca.storage;

import alpaca.ui.Ui;
import alpaca.tasks.*;
import alpaca.exceptions.InvalidFileException;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;


public class Storage {
    private static final String FILE_PATH = "data/Alpaca.txt";
    private static final File file = new File(FILE_PATH);

    private static int isTaskDone; //keep track of task status in the file

    public static boolean isFileExist() {
        return file.exists();
    }

    private static void readFileContents(TaskList tasks) throws IOException {
        try (Scanner s = new Scanner(file)) {
            while (s.hasNext()) {
                tasks.addTask(textToTask(s.nextLine()));
                if (isTaskDone == 1) {
                    tasks.markLastTask();//mark the task added
                }
            }
        } catch (InvalidFileException e) {
            Ui.printErrorMessage(e.toString());
        }
    }

    private static Task textToTask(String text) throws InvalidFileException {
        String[] split = text.split("\\|");
        isTaskDone = Integer.parseInt(split[1].strip());//keep track of task status in the file
        switch (split[0].strip()) {
        case "T":
            return new Todo(split[2].strip());

        case "D":
            return new Deadline(split[2].strip(), split[3].strip());

        case "E":
            String[] date = split[3].split("-");
            return new Event(split[2].strip(), date[0].strip(), date[1].strip());

        default:
            throw new InvalidFileException();
        }
    }

    public static void createEmptyFile() {
        try {
            if (file.createNewFile()) { // Use the shared File instance
                System.out.println("No existing task list, file created: " + file.getName());
                Ui.printLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            Ui.printLine();
        }
    }

    public static TaskList startFileReader(TaskList tasks) {
        try {
            readFileContents(tasks);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            Ui.printLine();
        }
        return tasks;
    }

    public static TaskList restoreTask() {
        TaskList tasks = new TaskList();
        startFileReader(tasks);
        return tasks;
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void startFileWriter(String taskList) {
        String filePath = "data/Alpaca.txt";
        try {
            writeToFile(filePath, taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
