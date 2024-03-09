package soot.storage;

import soot.exceptions.SavedFileErrorTypeException;
import soot.task.Task;
import soot.task.TaskType;
import soot.task.TaskList;
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
    private static final String SAVED_FILE_PATH = "./saved-data/saved.txt";
    private static final String SAVED_FILE_DIRECTORY_PATH = "./saved-data";

    public Storage(TaskList taskList) {
        loadSavedFile(taskList);
    }

    public static void loadSavedFile(TaskList taskList) {
        try {
            ArrayList<String> savedFile = Storage.readSavedFile(SAVED_FILE_PATH);
            for (String s : savedFile) {
                Storage.loadOneSavedTask(s);
            }
        } catch (FileNotFoundException e) {
            UserUi.printMessageWithDivider("there was no previously saved task list!");
            createFileDirectory();
        }
    }

    public static void createFileDirectory() {
        File fileDirectory = new File(SAVED_FILE_DIRECTORY_PATH);
        if (fileDirectory.exists()) {
            return;
        }

        boolean hasCreatedDir = fileDirectory.mkdir();
        if (!hasCreatedDir) {
            UserUi.printMessageWithDivider("there was a problem trying to create a saved file");
        }
    }

    /**
     * Returns an ArrayList of Strings.
     * Each string is a line read from the saved file in the given path.
     *
     * @param filePath relative path from project source of the saved file.
     * @return ArrayList of String that contains each line of the saved file.
     * @throws FileNotFoundException If no file was found at the specified path.
     */
    public static ArrayList<String> readSavedFile(String filePath) throws FileNotFoundException {
        ArrayList<String> readFile = new ArrayList<>();
        File savedFile = new File(filePath);

        Scanner s = new Scanner(savedFile);
        while (s.hasNext()) {
            readFile.add(s.nextLine());
        }
        s.close();
        UserUi.printMessageWithDivider("a previously saved file was found!\n" +
                "your data will be loaded in now :)");
        return readFile;
    }

    /**
     * Loads the saved task into the required format of an ArrayList of Tasks.
     * The details of the saved task will be extracted from the given string, depending on the Task Type.
     *
     * If there was an error with the saved file, all saved tasks will be deleted, including those already added to
     * the ArrayList.
     *
     * @param line string that contains only one line of the saved file, and thus one task.
     */
    public static void loadOneSavedTask(String line) {
        try {
            String[] savedTaskInfo = line.split(" ; ", 0);

            TaskType savedTaskType = Task.identifyTaskType(savedTaskInfo[0].trim());
            boolean isTaskDone = !savedTaskInfo[1].trim().equals("0");
            String taskName = savedTaskInfo[2];

            if (savedTaskType == TaskType.TODO) {
                TaskList.addSavedTodoTask(taskName, isTaskDone);
            } else if (savedTaskType == TaskType.DEADLINE) {
                String deadlineDue = savedTaskInfo[3];
                TaskList.addSavedDeadlineTask(taskName, isTaskDone, deadlineDue);
            } else if (savedTaskType == TaskType.EVENT) {
                String eventStartDate = savedTaskInfo[3];
                String eventEndDate = savedTaskInfo[4];
                TaskList.addSavedEventTask(taskName, isTaskDone, eventStartDate, eventEndDate);
            }
        } catch (SavedFileErrorTypeException e) {
            UserUi.printMessageWithDivider("!! there was an error with the previously saved file, " +
                    "sorry about it :o \nunfortunately, i will have to get rid of the previous data...");
            TaskList.clearTaskList();
        }
    }

    public static void tryToSaveFile() {
        try {
            saveFinalFile();
        } catch (IOException e) {
            System.out.println("there was a problem saving your tasks to the hard disk...");
        }
    }

    /**
     * Save all tasks in the task list into a file when the user indicated to stop the chatbot.
     * Tasks will be formatted to be stored as one line per task, containing all the task details.
     *
     * @throws IOException If there was a problem when saving the file.
     */
    public static void saveFinalFile() throws IOException {
        FileWriter fw = new FileWriter(SAVED_FILE_PATH);

        for (int i = 0; i < TaskList.getSize(); i++) {
            String stringToSave = TaskList.formatTaskToSave(i);
            fw.write(stringToSave + "\n");
        }
        fw.close();
    }
}