package soot.storage;

import soot.exceptions.SavedFileErrorTypeException;
import soot.task.*;
import soot.ui.UserUi;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class Storage handles the reading and writing of the tasks in the stored data file.
 * The stored tasks will be loaded and added into an ArrayList.
 */
public class Storage {
    /**
     * Reads the saved file in the given path line by line using a scanner,
     * and stores each line as a String in an ArrayList.
     *
     * @param path relative path from project source of the saved file.
     * @return ArrayList of String that contains each line of the saved file.
     * @throws FileNotFoundException If no file was found at the specified path.
     */
    public static ArrayList<String> readSavedFile(String path) throws FileNotFoundException {
        ArrayList<String> readFile = new ArrayList<>();
        File savedFile = new File(path);
        Scanner s = new Scanner(savedFile);
        while (s.hasNext()) {
            readFile.add(s.nextLine());
        }
        s.close();
        System.out.println("a previously saved file was found!\nyour data will be loaded in now :)");
        UserUi.displayDividerLine();
        return readFile;
    }

    /**
     * Loads the saved task into the required format of an ArrayList of Tasks.
     * The details of the saved task will be extracted from the given string, depending on the Task Type.
     *
     * @param line string that contains only one line of the saved file, and thus one task.
     */
    // TODO: create a getTaskDescription method
    public static void loadSavedTasks(String line) {
        try {
            String[] taskInfo = line.split(" ; ", 0);
            boolean isTaskDone = !taskInfo[1].trim().equals("0");

            TaskType savedTaskType = Task.identifyTaskType(taskInfo[0].trim());
            if (savedTaskType == TaskType.TODO) {
                TaskList.addSavedTodoTask(taskInfo[2], isTaskDone);
            } else if (savedTaskType == TaskType.DEADLINE) {
                TaskList.addSavedDeadlineTask(taskInfo[2], isTaskDone, taskInfo[3]);
            } else if (savedTaskType == TaskType.EVENT) {
                TaskList.addSavedEventTask(taskInfo[2], isTaskDone, taskInfo[3], taskInfo[4]);
            }
        } catch (SavedFileErrorTypeException e) {
            System.out.println("there was an error with the previously saved file, sorry about it :o \n " +
                            "unfortunately, i will have to get rid of the previous data...");
            UserUi.displayDividerLine();
            File savedData = new File("saved-data/saved.txt");
        }
    }

    /**
     * Save all tasks in the task list into a specified file first when the user indicated to stop the chatbot.
     * Tasks will be formatted to be stored as one line per task, with all the task details.
     *
     * @throws IOException If there was a problem when saving the file.
     */
    public static void saveFinalFile() throws IOException {
        FileWriter fw = new FileWriter("saved-data/saved.txt");

        for (int i = 0; i < TaskList.getSize(); i++) {
            String stringToSave = TaskList.formatTaskToSave(i);
            fw.write(stringToSave + "\n");
        }
        fw.close();
    }
}