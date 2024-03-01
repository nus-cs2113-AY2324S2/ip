import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 * Defines the class that writes and reads information from the txt file that stores the user's list.
 */
public class Storage {
    private static final String FILE_PATH = "Data/Jeff.txt";
    private static ArrayList<Task> savedTasks;
    private static Ui userInterface;


    /**
     * Creates an instance of the Storage class and stores a list of tasks and a UI instance to be used.
     *
     * @param tasksToSave List of tasks created by the user
     * @param uiComponent Instance of the Ui class
     */
    public Storage(ArrayList<Task> tasksToSave, Ui uiComponent){
        savedTasks = tasksToSave;
        userInterface = uiComponent;
    }


    public void setSavedList(ArrayList<Task> tasksToSave){
        savedTasks = tasksToSave;
    }


    public ArrayList<Task> getSavedList(){
        return savedTasks;
    }


    /**
     * Stores the user created list, in string form, in the txt file.
     */
    public void uploadTasks() {
        List<String> serialisedList = serialiseTasks();
        try {
            Path path = Paths.get(FILE_PATH);
            Files.createDirectories(path.getParent());
            Files.write(path, serialisedList);
            userInterface.successfulUploadMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Serializes the list of tasks from the ArrayList data structure to a list of strings.
     *
     * @return List of strings representing the serialised array of tasks.
     */
    private List<String> serialiseTasks(){ //turns the list of tasks into strings
        List<String> serialisedTasks = new ArrayList<>();
        for(Task task : savedTasks){
            serialisedTasks.add(task.getDescription());
        }
        return serialisedTasks;
    }


    /**
     * Deserializes the string data of a To-do task into a To-do Task in the data structure format.
     *
     * @param description Description of the To-do task.
     * @param task Referenced task object for the created task to be stored.
     * @param isDone Information of the completeness of the To-do task.
     * @param line Raw serialised data from the storage to be Parsed by the method.
     * @return Task to be added to a list.
     */
    private static Task deserializeTodo(String description, Task task, boolean isDone, String line){
        description = line.substring(6).trim();
        return new Todo(description, isDone);
    }

    /**
     * Deserializes the string data of a deadline task into a deadline Task in the data structure format.
     * Returns null if "by" time is not in the string
     *
     * @param description Description of the deadline task.
     * @param task Referenced task object for the created task to be stored.
     * @param isDone Information of the completeness of the deadline task.
     * @param line Raw serialised data from the storage to be Parsed by the method.
     * @return Task to be added to a list.
     */
    private static Task deserializeDeadline(String description, Task task, boolean isDone, String line) {
        int byIndex = line.indexOf("(by:");
        if (byIndex != -1) {
            description = line.substring(6, byIndex).trim();
            String by = line.substring(byIndex + 4, line.length() - 1).trim();
            return new Deadline(description, by, isDone);
        }
        return null;
    }

    /**
     * Deserializes the string data of an event task into an event Task in the data structure format.
     * Returns null if "from" time is not in the string
     *
     * @param description Description of the event task.
     * @param task Referenced task object for the created task to be stored.
     * @param isDone Information of the completeness of the event task.
     * @param line Raw serialised data from the storage to be Parsed by the method.
     * @return Task to be added to a list.
     */
    private static Task deserializeEvent(String description, Task task, boolean isDone, String line){
        int atIndex = line.indexOf("(from: ");
        if (atIndex != -1) {
            description = line.substring(6, atIndex).trim();
            String timeInfo = line.substring(atIndex + 6, line.length() - 1);
            String[] times = timeInfo.split(" to: ");
            if (times.length == 2) {
                String start = times[0].trim();
                String end = times[1].trim();
                return new Event(description, start, end, isDone);
            }
        }
        return null;
    }

    /**
     * Creates the list of tasks in the data structure format from the stored txt file format.
     */
    public static void deserializeTasks() {
        List<String> lines;
        savedTasks = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(FILE_PATH));
        } catch (IOException e) {
            userInterface.showFileNotFoundException();
            return;
        }
        for (String line : lines) {
            char taskType = line.charAt(1);
            boolean isDone = line.charAt(3) == 'X';
            String description = "";
            Task task = null;

            switch (taskType) {
                case 'T':
                    task = deserializeTodo(description, task, isDone, line);
                    break;

                case 'D':
                    task = deserializeDeadline(description, task, isDone, line);
                    break;

                case 'E':
                    task = deserializeEvent(description, task, isDone, line);
                    break;
            }
            if (task != null) {
                savedTasks.add(task);
            }
        }
    }
}


