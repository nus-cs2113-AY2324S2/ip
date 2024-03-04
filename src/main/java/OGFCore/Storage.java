package OGFCore;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import OGFTask.*;
// Manages file creation, reading and writing from file

/**
 * Class that manages creation, reading and writing from hard drive file
 */
public class Storage {
    private static final String STORAGE_PATH_NAME = "data/tasklist.txt";

    private final File storedList = new File(STORAGE_PATH_NAME);

    public Storage() throws OGFException, IOException {
        if (!storedList.exists()) {
            if (!(storedList.getParentFile().mkdirs() && storedList.createNewFile())) {
                throw new OGFException("Encountered an error trying to import storage file, ending program ", true); //fatal exception
            }
        }
    }

    /**
     * Converts a line from the text file to a OGFTask.Task
     * @param data line from the hardware file
     * @return OGFTask.Task
     * @throws OGFException Used for all errors
     */
    private static Task parseStoredTask(String data) throws OGFException {
        String[] params = data.split(",");
        switch (params[0]) {
            case ("todo"):
                return new Todo(params[1], Boolean.parseBoolean(params[2]));
            case ("deadline"):
                return new Deadline(params[1], params[3], Boolean.parseBoolean(params[2]));
            case ("event"):
                return new Event(params[1], params[3], params[4], Boolean.parseBoolean(params[2]));
            default:
                throw (new OGFException("Looks like your storage file is corrupted, ending program. \n Corrupted line: " + data, true)); //fatal exception
        }
    }

    /**
     * Converts the entire text file to a OGFCore.TaskList to be used by the program
     * @return OGFCore.TaskList
     * @throws IOException Fatal exception that should be solved by the user
     * @throws OGFException Used for all other errors
     */
    public TaskList readFile() throws IOException, OGFException { //ioexceptions should be fatal
        Scanner storageScanner = new Scanner(storedList);
        TaskList newList = new TaskList();
        while (storageScanner.hasNext()) {
            newList.addTask(parseStoredTask(storageScanner.nextLine()));
        }
        return newList;
    }

    /**
     * Writes text to file
     * @param textToAdd Text to write to file
     * @param willAppend True will cause the text to be appended to file, false would cause the text to overwrite file
     * @throws java.io.IOException Fatal exception that should be solved by the user
     */
    private static void writeToFile(String textToAdd, boolean willAppend) throws java.io.IOException{
            FileWriter fw = new FileWriter(STORAGE_PATH_NAME, willAppend);
            fw.write(textToAdd);
            fw.close();

    }

    /**
     * Overwrites text file with OGFCore.TaskList from the program
     * @param newerList OGFCore.TaskList from the Program
     * @throws IOException Fatal exception that should be solved by the user
     */
    public void updateFile(TaskList newerList) throws IOException {
        String listSerial = "";
        for (Task task : newerList.getTaskList()) {
            listSerial = listSerial + task.toSerial() + System.lineSeparator();
        }
        writeToFile(listSerial, false);
    }

    /**
     * Appends task to file
     * @param taskToAdd OGFTask.Task to add to file
     * @throws IOException Fatal exception that should be solved by the user
     */
    public void appendToFile(Task taskToAdd) throws IOException{
        writeToFile(taskToAdd.toSerial(), true);
    }
}



