package bossman.storage;

import bossman.task.Deadline;
import bossman.task.Task;
import bossman.task.TaskList;
import bossman.task.Todo;
import bossman.task.Event;
import bossman.ui.Ui;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Storage class handles the loading and saving of tasks from and to a file.
 */
public class Storage {
    public final TaskList TASK_LIST;
    private static final String FILE_PATH = "data/BossMan.txt";

    /**
     * Constructs a Storage object.
     * Initializes the TaskList and loads tasks from the file.
     *
     * @throws IOException if an I/O error occurs when accessing the file
     */
    public Storage() throws IOException {
        this.TASK_LIST = new TaskList();
        loadTasksFromFile();
    }

    /**
     * Loads tasks from the file and adds them to the TaskList.
     * If file is not found, create new file.
     *
     * @throws IOException if an I/O error occurs when accessing the file
     */
    protected void loadTasksFromFile() throws IOException {
        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String taskCsvFormat = scanner.nextLine();

                String[] formattedData = parseDataCsvFormat(taskCsvFormat);

                String taskType = formattedData[0];
                boolean isMark = Boolean.parseBoolean(formattedData[1]);
                String description = formattedData[2];

                switch (taskType) {
                case "T":
                    Task todoTask = new Todo(description, isMark);
                    TASK_LIST.addTask(todoTask);
                    break;

                case "D":
                    LocalDate by = LocalDate.parse(formattedData[3].trim());
                    Task deadlineTask = new Deadline(description, isMark, by);
                    TASK_LIST.addTask(deadlineTask);
                    break;

                case "E":
                    String from = formattedData[3];
                    String to = formattedData[4];
                    Task eventTask = new Event(description, isMark, from, to);
                    TASK_LIST.addTask(eventTask);
                    break;

                default:
                    Ui.printMessageNoSepNewLine("Invalid task");
                }
            }
        } catch (FileNotFoundException e) {
            createFolder();
        }
    }

    /**
     * Creates the folder and file if they do not exist.
     *
     * @throws IOException if an I/O error occurs when creating the file
     */
    public static void createFolder() throws IOException {
        File f = new File(FILE_PATH);
        f.getParentFile().mkdirs();
        f.createNewFile();
    }

    /**
     * Saves tasks from the TaskList to the file.
     *
     * @throws IOException if an I/O error occurs when accessing the file
     */
    public void saveTasksToFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task currTask : TASK_LIST.getTasks()) {
            fw.write(currTask.formatForSave() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Parses the CSV-formatted data and returns an array of strings.
     *
     * @param csvInput the CSV-formatted input string
     * @return an array of strings parsed from the input
     */
    private String[] parseDataCsvFormat(String csvInput) {
        return csvInput.trim().split(",", 5);
    }
}
