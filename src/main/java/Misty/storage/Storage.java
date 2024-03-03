package misty.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import misty.data.TaskList;
import misty.data.exception.CorruptedFileException;
import misty.data.task.Deadline;
import misty.data.task.Event;
import misty.data.task.Todo;

/**
 * Saves changes to task list to hard disk as a txt file.
 */
public class Storage {
    private final static  String DEFAULT_DATA_FOLDER_NAME = "data";
    private final static String DEFAULT_DATA_FILE_NAME = "misty.txt";
    private String filePath;
    private File dir = new File(DEFAULT_DATA_FOLDER_NAME);
    private File dataFile;

    /**
     * Constructs Storage object.
     */
    public Storage() {
        this.filePath = DEFAULT_DATA_FOLDER_NAME + "/" + DEFAULT_DATA_FILE_NAME;
        dataFile = new File(filePath);
    }

    /**
     * Creates file used to store data.
     *
     * @throws IOException If IO error has occurred.
     * @throws SecurityException If access permission are not given.
     */
    public void createFiles() throws IOException, SecurityException {
        if (!dir.exists()) {
            dir.mkdir();
        }

        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }
    }

    /**
     * Writes to save file.
     *
     * @param data Data to be written to save file.
     * @throws IOException If IO error has occurred.
     */
    private void writeToFile(String data) throws IOException {
        FileWriter fileWriter = new FileWriter(dataFile, true);
        fileWriter.write(data);
        fileWriter.close();
    }

    /**
     * Loads data from save file to task list in chatbot.
     *
     * @param taskList TaskList object containing all tasks.
     * @throws FileNotFoundException If save file is not found.
     * @throws CorruptedFileException If format of data in save file is incorrect.
     */
    public void loadData(TaskList taskList) throws FileNotFoundException, CorruptedFileException {
        Scanner scanner = new Scanner(dataFile);
        int taskCount = 0;
        String[] parameters;

        while (scanner.hasNext()) {
            String taskData = scanner.nextLine();
            parameters = taskData.split("\\|");
            if (parameters[0].contains("T")) {
                taskList.loadTodo(parameters[2].trim());
                taskCount++;

                loadMarkIfNeeded(taskList, parameters, taskCount);
            } else if (parameters[0].contains("D")) {
                taskList.loadDeadline(parameters[2].trim(), parameters[3].trim());
                taskCount++;

                loadMarkIfNeeded(taskList, parameters, taskCount);
            } else if (parameters[0].contains("E")) {
                taskList.loadEvent(parameters[2].trim(), parameters[3].trim(), parameters[4].trim());
                taskCount++;

                loadMarkIfNeeded(taskList, parameters, taskCount);
            } else {
                throw new CorruptedFileException();
            }
        }
    }

    private static void loadMarkIfNeeded(TaskList taskList, String[] parameters, int taskCount) throws CorruptedFileException {
        if (parameters[1].contains("1")) {
            taskList.loadMark(taskCount);
        }
    }

    /**
     * Saves todo task to save file.
     *
     * @param todo todo task to be saved.
     * @throws IOException If IO error has occurred.
     */
    public void saveTodo(Todo todo) throws IOException {
        String data = String.format("T | %s | %s\n",(todo.getIsDone() ? "1" : "0") ,todo.getTaskName());
        writeToFile(data);
    }

    /**
     * Saves deadline to save file.
     *
     * @param deadline deadline to be saved.
     * @throws IOException If IO error has occurred.
     */
    public void saveDeadLine(Deadline deadline) throws IOException {
        String data = String.format("D | %s | %s | %s\n",(deadline.getIsDone() ? "1" : "0")
                ,deadline.getTaskName(), deadline.getBy());
        writeToFile(data);
    }

    /**
     * Saves event to save file.
     *
     * @param event event to be saved.
     * @throws IOException If IO error has occurred.
     */
    public void saveEvent(Event event) throws IOException {
        String data = String.format("E | %s | %s | %s | %s\n",(event.getIsDone() ? "1" : "0")
                ,event.getTaskName(), event.getFrom(), event.getTo());
        writeToFile(data);
    }

    /**
     * Overwrites data in save file with task list data stored in chatbot.
     *
     * @param list ArrayList containing all tasks.
     * @throws IOException If IO error has occurred.
     */
    public void refreshSave(ArrayList list) throws IOException {
        FileWriter fileWriter = new FileWriter(dataFile);
        fileWriter.write("");

        for (int i = 0 ; i < list.size() ; i++ ) {
            if (list.get(i) instanceof Todo) {
                saveTodo((Todo)list.get(i));
            } else if (list.get(i) instanceof Deadline) {
                saveDeadLine((Deadline)list.get(i));
            } else if (list.get(i) instanceof Event) {
                saveEvent((Event)list.get(i));
            }
        }
    }
}