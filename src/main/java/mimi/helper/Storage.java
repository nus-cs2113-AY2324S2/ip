package mimi.helper;

import mimi.classes.Deadline;
import mimi.classes.Event;
import mimi.classes.Task;
import mimi.classes.ToDo;
import mimi.exceptions.MimiException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static mimi.helper.TaskList.appendIntoTaskList;

public class Storage {

    private String filePath;
    public final static String FILE_DELIMINITER = "|";
    public final static String DEADLINE_DELIMITER = "/by";
    public Storage (String filePath){
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    private static Scanner createFileScanner(String filePath) throws MimiException.LoadError {
        try{
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);
            fileScanner.useDelimiter("\n");
            return fileScanner;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new MimiException.LoadError(MimiException.LOAD_ERROR_MSG);
        }
    }



    private static String[] processTask(String[] task) throws
            MimiException.IncorrectFormat,
            MimiException.InsufficientParameters,
            MimiException.FileCorrupted{

        if (task.length < 3) {
            throw new MimiException.FileCorrupted(MimiException.FILE_CORRUPTED_MSG);
        }

        // Task [1] refers to the status of the task. Checking if the status is invalid
        if (!task[1].equals("true") && !task[1].equals("false")) {
            throw new MimiException.FileCorrupted(MimiException.FILE_CORRUPTED_MSG);
        }
        String [] taskDetails = new String[3];
        taskDetails[0] = task[0]; //taskType
        taskDetails[1] = task[1]; // taskStatus
        taskDetails[2] = task[2]; // taskName
        return taskDetails;
    }
    public void loadFile() throws MimiException.FileCorrupted {
        try {
            Scanner fileScanner = createFileScanner(this.filePath);

            while (fileScanner.hasNext()) {
                String[] task = fileScanner.next().split("\\" + FILE_DELIMINITER);
                String[] taskDetails = processTask(task);
                String taskType = taskDetails[0];
                String taskStatus = taskDetails[1];
                String taskName = taskDetails[2];
                switch (taskType) {
                case "T":
                    ToDo toDo = Parser.processToDoFromStorage(taskName, taskStatus);
                    appendIntoTaskList(toDo);
                    break;
                case "D":
                    Deadline deadline = Parser.processDeadlineFromStorage(task);
                    appendIntoTaskList(deadline);
                    break;
                case "E":
                    Event event = Parser.processEventFromStorage(task);
                    appendIntoTaskList(event);
                    break;
                default:
                    throw new MimiException.FileCorrupted(MimiException.FILE_CORRUPTED_MSG);
                }

                if (taskStatus.equals("true")) {
                    ArrayList<Task> currentList = TaskList.getTaskList();
                    currentList.get(currentList.size() - 1).markAsDone();
                }
            }
            this.saveFile(TaskList.getTaskList(), filePath);

        } catch (MimiException.LoadError | MimiException.IncorrectFormat | MimiException.InsufficientParameters | ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveFile(ArrayList<Task> taskList, String filePath) {
        try {
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file);
            for (Task t : taskList) {
                writer.write(t.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("\u001B[31mError:Unable to save file as " + filePath + " does not exists. Please create a " +
                    "/data folder in the root directory first.\u001B[0m");
        }
    }

}
