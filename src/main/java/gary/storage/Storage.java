package gary.storage;

import gary.task.Task;
import gary.task.Deadline;
import gary.task.Event;
import gary.task.Todo;
import gary.task.TaskType;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Storage class deals with storing users' tasks and its details when users add or modify tasks
 * into a txt file.
 */
public class Storage {
    public static final String FILE_PATH = "./gary.txt";
    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Create a txt file at a given file path when file doesn't exist, or
     * open the existing txt file.
     *
     * @return txt file.
     */
    public static File createFile() {
        File file = new File(FILE_PATH);
        try {
            Boolean isFileCreated = file.createNewFile();
        } catch (IOException e) {
            System.out.println("FILE NOT CREATED");
        }
        return file;
    }

    /**
     * Read txt files that store users' tasks and store in ArrayList to be processed while
     * programme is running.
     *
     * @param file txt file to store the tasks.
     * @return ArrayList consisting of all tasks users have.
     */
    public static ArrayList<Task> readFileStorage(File file) {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String lineText = fileReader.readLine();
            String[] lineWords;
            String command;
            int tasksCount = 0;

            while (lineText != null) {
                // convert each line into TASK_todo/deadline/event, then store in the array list todos
                lineWords = lineText.split(" \\| ");
                command = lineWords[0];
                String description = lineWords[2];

                if (command.equalsIgnoreCase("TODO")) {
                    tasks.add(new Todo(description));
                } else if (command.equalsIgnoreCase("DEADLINE")) {
                    String by = lineWords[3];
                    tasks.add(new Deadline(description, by));
                } else if (command.equalsIgnoreCase("EVENT")){
                    String from = lineWords[3];
                    String to = lineWords[4];
                    tasks.add(new Event(description, from, to));
                }
                tasksCount += 1;

                // Update task status in array list todos
                String taskStatus = lineWords[1];
                if (taskStatus.equalsIgnoreCase("1")) {
                    Task currentTask = tasks.get(tasksCount - 1);
                    currentTask.markAsDone();
                }

                lineText = fileReader.readLine();
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND!!!");
        } catch (IOException e) {
            System.out.println("Failed to read file");
        }
        return tasks;
    }

    /**
     * Write the tasks inside the ArrayList todos into the txt file. Handles error
     * if file trying to be written to doesn't exist or there's failure when writing the file.
     *
     * @param file txt file to store the tasks.
     * @param tasksCount number of tasks in the array list.
     * @param tasks array list that stores and manages the task while programme is running.
     */
    public static void writeTaskToTxt(File file, int tasksCount, ArrayList<Task> tasks) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file, false));
            for (int i = 0; i < tasksCount; i += 1) {
                writeFormattedString(tasks, i, fileWriter);
            }
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND!!!");
        } catch (IOException e) {
            System.out.println("Failed to write to file");
        }
    }

    /**
     * Format the task stored in ArrayList and write it into the given txt file. Throws
     * exception when writing process failed.
     *
     * @param tasks array list that stores and manages the task while programme is running.
     * @param taskIndex index of task.
     * @param fileWriter txt file opened as write.
     * @throws IOException if writing process failed or interrupted.
     */
    private static void writeFormattedString(ArrayList<Task> tasks, int taskIndex, BufferedWriter fileWriter)
            throws IOException {
        Task currentTask = tasks.get(taskIndex);
        String description = currentTask.getTaskDescription();
        String taskStatus = currentTask.getTaskStatus() ? "1" : "0";
        TaskType taskType = currentTask.getTaskType();

        switch(taskType) {
        case DEADLINE:
            Deadline deadline = (Deadline) currentTask;
            String by = deadline.getBy();
            fileWriter.write(taskType + " | " + taskStatus + " | "
                    + description + " | " + by + "\n");
            break;
        case EVENT:
            Event event = (Event) currentTask;
            String from = event.getFrom();
            String to = event.getTo();
            fileWriter.write(taskType + " | " + taskStatus + " | "
                    + description + " | " + from + " | " + to + "\n");
            break;
        case TODO:
            fileWriter.write(taskType + " | " + taskStatus + " | " + description + "\n");
            break;
        }
    }
}
