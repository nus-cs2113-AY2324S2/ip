package filemanager;

import taskmanager.Parser;
import taskmanager.Task;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static int loadTasksFromFile(File saveFile, int taskCounter, ArrayList<Task> taskList) {
        //load data from save file to taskList
        //File saveFile = new File("./Serf.txt");
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
                    //System.out.println(loadMessage.nextLine());
                } else if (store.contains("deadline")) {
                    taskCounter = Parser.addDeadlineTaskToList(store, taskList, taskCounter);
                    //System.out.println(loadMessage.nextLine());
                } else if (store.contains("event")) {
                    taskCounter = Parser.addEventTaskToList(store, taskList, taskCounter);
                    //System.out.println(loadMessage.nextLine());
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
        return taskCounter;
    }

    public static void saveTaskListToFile(int taskCounter, ArrayList<Task> taskList) {
        FileWriter loadFile = null;
        try {
            loadFile = new FileWriter( "./Serf.txt", true);
            for (int iterator = 0; iterator < taskCounter; iterator += 1) {
                if (taskList.get(iterator).getTaskType().equals("T")) {
                    loadFile.write("todo " + taskList.get(iterator).getDescription() + System.lineSeparator());
                } else if (taskList.get(iterator).getTaskType().equals("D")) {
                    loadFile.write("deadline " + taskList.get(iterator).getDescription()
                            + " /by " + taskList.get(iterator).getEndDate() + System.lineSeparator());
                } else if (taskList.get(iterator).getTaskType().equals("E")) {
                    loadFile.write("event " + taskList.get(iterator).getDescription()
                            + " /from " + taskList.get(iterator).getStartDate()
                            + " /to " + taskList.get(iterator).getEndDate() + System.lineSeparator());
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
