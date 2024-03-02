import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// Manages file creation, reading and writing from file
public class Storage {
    private static final String STORAGE_PATH_NAME = "data/tasklist.txt";

    private File storedList = new File(STORAGE_PATH_NAME);

    public Storage() throws OGFException, IOException {
        if (!storedList.exists()) {
            if (!(storedList.getParentFile().mkdirs() && storedList.createNewFile())) {
                throw new OGFException("Encountered an error trying to import storage file, ending program ", true); //fatal exception
            }
        }
    }

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

    public TaskList readFile() throws IOException, OGFException { //ioexceptions should be fatal
        Scanner storageScanner = new Scanner(storedList);
        TaskList newList = new TaskList();
        while (storageScanner.hasNext()) {
            newList.addTask(parseStoredTask(storageScanner.nextLine()));
        }
        return newList;
    }

    private static void writeToFile(String textToAdd, boolean willAppend) throws java.io.IOException{
            FileWriter fw = new FileWriter(STORAGE_PATH_NAME, willAppend);
            fw.write(textToAdd);
            fw.close();

    }

    public void updateFile(TaskList newerList) throws IOException {
        String listSerial = "";
        for (Task task : newerList.getTaskList()) {
            listSerial = listSerial + task.toSerial() + System.lineSeparator();
        }
        writeToFile(listSerial, false);
    }

    public void appendToFile(Task taskToAdd) throws IOException{
        writeToFile(taskToAdd.toSerial(), true);
    }
}



