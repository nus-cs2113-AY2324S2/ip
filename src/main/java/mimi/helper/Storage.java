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

/**
 * This class is used to handle the loading and saving of tasks to the /data/mimi.logs file.*
 *
 * @author Justin
 * @version 0.2
 * @since 0.2
 */
public class Storage {

    private final String filePath;
    public final static String FILE_DELIMITER = "|";
    public final static String DEADLINE_DELIMITER = "/by";
    public Storage (String filePath){
        this.filePath = filePath;
    }


    /**
     * This method creates a scanner object to read the file.
     *
     * @param filePath the path of the file to be read
     * @return a scanner object to read the file
     * @throws MimiException.LoadError if the file/directory is not found
     */
    private static Scanner createFileScanner(String filePath) throws MimiException.LoadError {
        try{
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);
            fileScanner.useDelimiter("\n");
            return fileScanner;
        } catch (IOException e) {
            throw new MimiException.LoadError(MimiException.LOAD_ERROR_MSG);
        }
    }

    /**
     * This method is used to check if the task parameters are in the correct format.
     *
     * @param task  a String array in the form of {taskType, taskStatus, taskName}
     * @return      a String array in the form of {taskType, taskStatus, taskName}
     * @throws MimiException.IncorrectFormat if the task is in the wrong format
     * @throws MimiException.FileCorrupted if the file is corrupted
     */

    private static String[] processTask(String[] task) throws
            MimiException.IncorrectFormat,
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

    /**
     * Loads the /data/mimi.logs file, processes the tasks, and
     * adds them to a static taskList in the TaskList class.
     *
     * @throws MimiException.FileCorrupted if the file is corrupted and cannot be properly read or parsed
     **/
    public void loadFile() throws MimiException.FileCorrupted {
        try {
            Scanner fileScanner = createFileScanner(this.filePath);

            while (fileScanner.hasNext()) {
                String[] task = fileScanner.next().split("\\" + FILE_DELIMITER);
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

    /**
     * This method is called everytime a user make changes to the static taskList object in the TaskList class.
     * It saves the taskList to the /data/mimi.logs file.
     *
     * @param taskList  the list of tasks to be saved
     * @param filePath  the path of the file to be saved
     */
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
