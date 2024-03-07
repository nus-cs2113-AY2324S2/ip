package Bobble;

import Bobble.task.Deadline;
import Bobble.task.Event;
import Bobble.task.Task;
import Bobble.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public String filePath;
    private static final String DIRECTORY_PATH = "./data";
    private static final String DIVIDER = "\\|";
    private ArrayList<Task> savedTaskList = new ArrayList<>();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
                // Parse each line by splitting it with "|"
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

    private void checkIfDone(String status) {
        if (status.equals("X")) {
            int index = savedTaskList.size();
            savedTaskList.get(index - 1).setDone(true);
        }
    }

    public void saveAddedTask(ArrayList<Task> taskList) {
        saveList(taskList.get(taskList.size() - 1).toString()); // Save tasks to file whenever the task list changes
    }

    public void saveWholeList(ArrayList<Task> TaskList) {
        try {
            writeToFile("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < TaskList.size(); i++) {
            saveList(TaskList.get(i).toString());
        }
    }

    private void saveList(String taskToString) {
        //for each task in ArrayList savedTaskList, get Class + isDone + Desc of each based on type.
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

    private static String formatTaskDescription(String taskToString, String taskType) {
        String description = taskToString.substring(7);
        if (taskType.equals("T")) {
            return description;
        }

        String formattedDescription = "";
        if (taskType.equals("D")) {
            String[] descAndBy = description.split("\\(by: ");
            formattedDescription = descAndBy[0] + "/by " + descAndBy[1].substring(0, descAndBy[1].length() - 1);
        }
        if (taskType.equals("E")) {
            String[] descAndTime = description.split("\\(from: ");
            String[] duration = descAndTime[1].split("to: ");
            formattedDescription = descAndTime[0] + "/from " + duration[0] + "/to " +
                    duration[1].substring(0, duration[1].length() - 1);
        }
        return formattedDescription;
    }

    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend + "\n");
        fw.close();
    }

}
