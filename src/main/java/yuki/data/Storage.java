package yuki.data;

import yuki.task.Deadline;
import yuki.task.Event;
import yuki.task.Task;
import yuki.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

import static yuki.Constants.*;

/**
 * Methods to read from and write to data file.
 */
public class Storage {

    private static File dataFile;
    private static String pathToFile;

    public Storage(String fileName) {
        dataFile = new File(fileName);
        pathToFile = fileName;
    }

    /**
     * Attempts to load tasks from input data file into an ArrayList of Task.
     */
    public ArrayList<Task> loadData() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            ArrayList<String> dataItems = readFile();
            taskList = parse(dataItems);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }


    /**
     * Attempts to write details of Tasks into data file.
     *
     * @param tasks ArrayList of Tasks.
     * @throws IOException if unable to create the output directory or output text file.
     */
    public static void writeFile(ArrayList<Task> tasks) throws IOException {

        if (dataFile.exists()) {
            System.out.println("Found data file.");
        } else if (!dataFile.getParentFile().exists()) {
            // create directory to save in.
            boolean hasDirectoryCreated = dataFile.getParentFile().mkdirs();
            if (!hasDirectoryCreated) {
                throw new IOException("Failed to create the 'data' directory.");
            }
            // create file to save in.
            boolean hasFileCreated = dataFile.createNewFile();
            if (!hasFileCreated) {
                throw new IOException("Failed to create the output text file.");
            }
        }

        FileWriter writer = new FileWriter(pathToFile);

        int index = 1;
        for (Task item : tasks) {
            writer.write((index) + ".[" + item.getStatusIcon() + "] "
                    + item.taskType + " " + item.description + "\n");
            index++;
        }
        System.out.println("Successfully written into data file.");
        writer.close();
    }

    /**
     * Finds and reads input file.
     */
    private ArrayList<String> readFile() throws IOException {
        if (!dataFile.exists()) {
            throw new FileNotFoundException("No input file found, will create a new one.");
        }
        if (dataFile.length() == 0) {
            System.out.println("empty file");
            throw new IOException();
        }
        return (ArrayList<String>) Files.readAllLines(dataFile.toPath(), Charset.defaultCharset());
    }


    /**
     * Parses content of input file.
     */
    private ArrayList<Task> parse(ArrayList<String> dataItems) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String line : dataItems) {
            String taskDescription = line.substring(TASK_DESCRIPTION_INDEX);
            char taskType = line.charAt(TASK_INDICATOR_INDEX);
            boolean isCompleted = line.charAt(TASK_COMPLETION_INDEX) == COMPLETION_INDICATOR;
            switch (taskType) {
            case TODO_INDICATOR:
                Todo todo = new Todo(taskDescription, isCompleted);
                tasks.add(todo);
                break;
            case DEADLINE_INDICATOR:
                Deadline deadline = new Deadline(taskDescription, isCompleted);
                tasks.add(deadline);
                break;
            case EVENT_INDICATOR:
                Event event = new Event(taskDescription, isCompleted);
                tasks.add(event);
                break;
            default:
                System.out.println("Invalid task indicator. Skipping.");
                break;
            }
        }
        return tasks;
    }

}
