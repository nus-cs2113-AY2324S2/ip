package mona.manager;

import mona.input.InputParser;
import mona.storage.TaskStorage;
import mona.output.ConsolePrint;
import mona.task.Deadline;
import mona.task.Event;
import mona.task.Task;
import mona.task.Todo;
import mona.util.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TaskManager {
    public static int noOfTasks;
    protected Task[] tasks;
    protected TaskStorage taskStorage;

    public TaskManager() {
        this.tasks = new Task[100];   //initialize an array of Tasks, to act as a list
        noOfTasks = 0;
        this.taskStorage = new TaskStorage(Constants.DATA_FILE_PATH);
    }
    public void executeCommand(String[] commandTypeAndParams) {
        String commandType = commandTypeAndParams[Constants.INDEX_COMMAND_TYPE];

        switch (commandType) {
        case ("mark"):
            int markIndex = Integer.parseInt(commandTypeAndParams[Constants.INDEX_DESCRIPTION]) - 1;
            tasks[markIndex].markAsDone();
            ConsolePrint.printMarkStatement(tasks[markIndex]);
            break;
        case ("unmark"):
            int unmarkIndex = Integer.parseInt(commandTypeAndParams[Constants.INDEX_DESCRIPTION]) - 1;
            tasks[unmarkIndex].markAsNotDone();
            ConsolePrint.printUnmarkStatement(tasks[unmarkIndex]);
            break;
        case ("list"):
            ConsolePrint.printList(tasks);
            break;
        case ("todo"):
            tasks[noOfTasks] = new Todo(commandTypeAndParams[Constants.INDEX_DESCRIPTION]);
            ConsolePrint.printAddTaskStatement(tasks[noOfTasks], noOfTasks + 1);
            noOfTasks += 1;
            break;
        case ("deadline"):
            tasks[noOfTasks] = new Deadline(commandTypeAndParams[Constants.INDEX_DESCRIPTION],
                    commandTypeAndParams[Constants.INDEX_DEADLINE]);
            ConsolePrint.printAddTaskStatement(tasks[noOfTasks], noOfTasks + 1);
            noOfTasks += 1;
            break;
        case ("event"):
            tasks[noOfTasks] = new Event(commandTypeAndParams[Constants.INDEX_DESCRIPTION],
                    commandTypeAndParams[Constants.INDEX_FROM_DATE],
                    commandTypeAndParams[Constants.INDEX_TO_DATE]);
            ConsolePrint.printAddTaskStatement(tasks[noOfTasks], noOfTasks + 1);
            noOfTasks += 1;
            break;
        default:
            taskStorage.saveToStorage(tasks);
        }
    }

    public void loadFromStorage() {
        File dataFolder = new File(Constants.DATA_FOLDER_PATH);
        File dataFile = new File(dataFolder, Constants.DATA_FILE_NAME);

        // Check if the data folder exists, if not create it
        if (!dataFolder.exists()) {
            boolean wasDirectoryMade = dataFolder.mkdirs();
            if (!wasDirectoryMade) {
                System.out.println("Could not create data directory.");
                return; // Stop further execution
            }
        }

        // Check if the data file exists, if not create it
        if (!dataFile.exists()) {
            try {
                boolean wasFileCreated = dataFile.createNewFile();
                if (!wasFileCreated) {
                    System.out.println("Could not create data file.");
                    return; // Stop further execution
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            File f = new File(Constants.DATA_FILE_PATH); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String nextLine = s.nextLine();

                String[] processedInput = taskStorage.parseSavedInput(nextLine);
                InputParser input = new InputParser(processedInput[0]);
                executeCommand(input.getCommandTypeAndParams());

                if (processedInput[1].equals("DONE")) {
                    tasks[noOfTasks - 1].markAsDone();
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
