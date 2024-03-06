package filemanager;

import taskmanager.Parser;
import taskmanager.Task;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Load and save your task list from and to the save file AKA storage
 */
public class Storage {

    /**
     * Retrieve tasks from save file and store them in the task list
     * returns taskCounter which keeps track of the number of task in the task list
     *
     * @param saveFile The file in which the tasks are saved
     * @param taskCounter Counter to keep track of the number of task in the task list
     * @param taskList List to store tasks for the program to use
     * @return Updated counter which keep track of the number of task in the task list
     */


    public static ArrayList<Task> loadTasksFromFile(File saveFile, int taskCounter, ArrayList<Task> taskList) {
        try {
            if (!saveFile.isFile()) {
                saveFile.createNewFile();
            }
        } catch (java.io.IOException e){
            System.out.println("     Sire I believe you have already created this file called 'Serf.txt'");
        }
        try {
            Scanner loadMessage = new Scanner(saveFile);

            while (loadMessage.hasNext()) {
                String store = loadMessage.nextLine();
                if (store.contains("todo")) {
                    taskCounter = Parser.addTodoTaskToList(store, taskList, taskCounter);
                } else if (store.contains("deadline")) {
                    taskCounter = Parser.addDeadlineTaskToList(store, taskList, taskCounter);
                } else if (store.contains("event")) {
                    taskCounter = Parser.addEventTaskToList(store, taskList, taskCounter);
                } else {
                    break;
                }
            }
            loadMessage.close();
        } catch (java.io.FileNotFoundException e){
            System.out.println("Sire, the file is not created yet");
        } catch (java.util.NoSuchElementException e) {
            System.out.println("Sire, there is nothing else in the save file to load");
        }
        return taskList;
    }

    /**
     * Load tasks from task list into the save file
     *
     * @param taskCounter Counter to keep track of the number of task in the task list
     * @param taskList List to store tasks for the program to use
     */

    public static void saveTaskListToFile(int taskCounter, ArrayList<Task> taskList) {
        FileWriter loadFile = null;
        try {
            loadFile = new FileWriter( "./Serf.txt", true);
            for (int iterator = 0; iterator < taskCounter; iterator += 1) {
                if (taskList.get(iterator).getTaskType().equals("T")) {
                    if (taskList.get(iterator).getStatusIcon().equals("X")) {
                        loadFile.write("mark " + "todo " + taskList.get(iterator).getDescription()
                                + System.lineSeparator());
                    } else {
                        loadFile.write("todo " + taskList.get(iterator).getDescription() + System.lineSeparator());
                    }
                } else if (taskList.get(iterator).getTaskType().equals("D")) {
                    if (taskList.get(iterator).getStatusIcon().equals("X")) {
                        loadFile.write("mark " + "deadline " + taskList.get(iterator).getDescription()
                                + " /by " + taskList.get(iterator).getEndDate() + System.lineSeparator());
                    } else {
                        loadFile.write("deadline " + taskList.get(iterator).getDescription()
                                + " /by " + taskList.get(iterator).getEndDate() + System.lineSeparator());
                    }
                } else if (taskList.get(iterator).getTaskType().equals("E")) {
                    if (taskList.get(iterator).getStatusIcon().equals("X")) {
                        loadFile.write("mark " + "event " + taskList.get(iterator).getDescription()
                                + " /from " + taskList.get(iterator).getStartDate()
                                + " /to " + taskList.get(iterator).getEndDate() + System.lineSeparator());
                    } else {
                        loadFile.write("event " + taskList.get(iterator).getDescription()
                                + " /from " + taskList.get(iterator).getStartDate()
                                + " /to " + taskList.get(iterator).getEndDate() + System.lineSeparator());
                    }
                } else {
                    System.out.println("error");
                }
            }
        } catch (java.io.IOException e) {
            System.out.println("Sire there is an error with the load file please try again");
        } finally {
            try {
                loadFile.flush();
                loadFile.close();
            } catch (java.io.IOException e){
                System.out.println("Sire there is an error with the load file please try again");
            }
        }
    }
}
