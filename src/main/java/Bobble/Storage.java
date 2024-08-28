package Bobble;

import Bobble.task.Deadline;
import Bobble.task.Event;
import Bobble.task.Task;
import Bobble.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class handles loading from and saving tasks to a file.
 */
public class Storage {
    private String filePath;
    private static final String DIRECTORY_PATH = "./data";
    private static final String DIVIDER = "\\|";
    private ArrayList<Task> savedTaskList = new ArrayList<>();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads saved tasks from the file.
     *
     * @return The list of tasks loaded from the file.
     * @throws IOException If an error occurs while reading the file.
     */
    public ArrayList<Task> load() throws IOException {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new IOException("Failed to create directory: " + directory.getAbsolutePath());
            }
        }

        File file = new File(this.filePath);

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            try {
                String savedTask = scanner.nextLine();
                String[] parts = savedTask.split(DIVIDER);
                switch (parts[0]) {
                case "T":
                    ToDo newToDo = new ToDo(parts[2]);
                    savedTaskList.add(newToDo);
                    checkIfDone(parts[1]);
                    break;
                case "D":
                    Deadline newDeadline = Parser.getNewDeadline(parts[2]);
                    savedTaskList.add(newDeadline);
                    checkIfDone(parts[1]);
                    break;
                case "E":
                    Event newEvent = Parser.getNewEvent(parts[2]);
                    savedTaskList.add(newEvent);
                    checkIfDone(parts[1]);
                    break;
                default:
                    throw new BobbleExceptionCommand();
                }
            } catch (BobbleExceptionCommand e) {
                throw new IOException("Failed to read task.");
            }
        }
        scanner.close();
        return savedTaskList;
    }

    /**
     * Checks if a task is marked as done and updates its status accordingly.
     *
     * @param status The status of the task (X for done, otherwise not done).
     */
    private void checkIfDone(String status) {
        if (status.equals("X")) {
            int index = savedTaskList.size();
            savedTaskList.get(index - 1).setDone(true);
        }
    }

    /**
     * Saves the added task to the file.
     *
     * @param taskList The updated task list.
     */
    public void saveAddedTask(ArrayList<Task> taskList) {
        saveList(taskList.get(taskList.size() - 1).toString());
    }


    /**
     * Rewrites and save the entire task list to the file.
     *
     * @param taskList The task list to be saved.
     */
    public void saveWholeList(ArrayList<Task> taskList) {
        try {
            writeToFile("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < taskList.size(); i++) {
            saveList(taskList.get(i).toString());
        }
    }


    /**
     * Saves a single task to the file.
     *
     * @param taskToString The string representation of the task to be saved.
     */
    private void saveList(String taskToString) {
        String taskType = taskToString.substring(1, 2);
        String taskStatus = taskToString.substring(4, 5);
        String description = formatTaskDescription(taskToString, taskType);
        String text = taskType + "|" + taskStatus + "|" + description;

        try {
            appendToFile(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Formats the task description based on its type to be saved into the file.
     *
     * @param taskToString The string representation of the task.
     * @param taskType The type of the task.
     * @return The formatted task description to be saved into the file.
     */
    private static String formatTaskDescription(String taskToString, String taskType) {
        String description = taskToString.substring(7);
        if (taskType.equals("T")) {
            return description;
        }

        String formattedDescription = "";
        if (taskType.equals("D")) {
            String[] descAndBy = description.split("\\(by: ");
            formattedDescription = descAndBy[0] + "/by " + descAndBy[1].substring(0, descAndBy[1].length() - 1);
        } else if (taskType.equals("E")) {
            String[] descAndTime = description.split("\\(from: ");
            String[] duration = descAndTime[1].split("to: ");
            formattedDescription = descAndTime[0] + "/from " + duration[0] + "/to " +
                    duration[1].substring(0, duration[1].length() - 1);
        }
        return formattedDescription;
    }

    /**
     * Writes text to the file.
     *
     * @param textToAdd The text to be written to the file.
     * @throws IOException If an error occurs while writing to the file.
     */
    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        fw.write(textToAdd);
        fw.close();
    }


    /**
     * Appends text to the file.
     *
     * @param textToAppend The text to be appended to the file.
     * @throws IOException If an error occurs while appending to the file.
     */
    private void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true);
        fw.write(textToAppend + "\n");
        fw.close();
    }

}
