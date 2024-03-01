package Bobble.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private static final String FILE_PATH = "./data/bobble.txt";
    private static final String DIRECTORY_PATH = "./data";
    private static final String DIVIDER = "\\|";
    private ArrayList<Task> savedTaskList;

    public FileManager() {
        savedTaskList = new ArrayList<>();
    }

    public ArrayList<Task> loadSavedList() throws IOException {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                // Failed to create the directory
                throw new IOException("Failed to create directory: " + directory.getAbsolutePath());
            }
        }

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            // If the file does not exist, create a new file and return an empty ArrayList
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return savedTaskList;
        }

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
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
                    String[] descAndBy = parts[2].split("/by");
                    Deadline newDeadline = new Deadline(descAndBy[0], descAndBy[1]);
                    savedTaskList.add(newDeadline);
                    checkIfDone(parts[1]);
                    break;
                case "E":
                    String[] descAndTime = parts[2].split("/from");
                    String[] duration = descAndTime[1].split("/to");
                    Event newEvent = new Event(descAndTime[0], duration[0], duration[1]);
                    savedTaskList.add(newEvent);
                    checkIfDone(parts[1]);
                    break;
                default:
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("error");
        }
        return savedTaskList;
    }

    private void checkIfDone(String status) {
        if (status.equals("X")) {
            int index = savedTaskList.size();
            savedTaskList.get(index - 1).setDone(true);
        }
    }

    public static void saveAddedTask(ArrayList<Task> taskList) {
        saveList(taskList.get(taskList.size() - 1).toString()); // Save tasks to file whenever the task list changes
    }

    public static void saveWholeList(ArrayList<Task> TaskList) {
        try {
            writeToFile("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < TaskList.size(); i++) {
            saveList(TaskList.get(i).toString());
        }
    }

    private static void saveList(String taskToString) {
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

    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(FileManager.FILE_PATH, true); // create a FileWriter in append mode
        fw.write(textToAppend + "\n");
        fw.close();
    }

}
