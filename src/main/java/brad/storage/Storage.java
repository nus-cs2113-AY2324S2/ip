package brad.storage;

import brad.exceptions.dataCorruptedException;
import brad.tasks.TaskType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;

import brad.tasks.Tasks;
import brad.tasks.TaskList;

public class Storage {
    private static final String DIRECTORY_PATH = Paths.get(".", "data").toString();
    private static final String FILE_NAME =  "data.md";
    private static final String FILE_PATH = Paths.get(DIRECTORY_PATH, FILE_NAME).toString();
    private static final File file = new File(FILE_PATH);

    private final String FILE_HEADER = "|Task Type | Done | Description | Time |\n"
            + "|----------|------|-------------|------|\n";

    private final String EVENT = "Event";
    private final String DEADLINE = "Deadline";
    private final String TODO = "Todo";
    /**
     * Create a new directory & file if not found.
     * Load file from file and interpret stored data and populate the tasklist
     * @param tasklist current list of tasks
     * @throws FileNotFoundException if file cannot be found
     * @throws dataCorruptedException if data in file is corrupted
     */
    public void initializeFile(TaskList tasklist)
            throws FileNotFoundException, dataCorruptedException {
        try {
            if (!file.exists()) {
                File directory = new File (DIRECTORY_PATH);
                directory.mkdir();
                file.createNewFile();
                addHeader();
                System.out.println("File created :" + file.getAbsolutePath());
                System.out.println("File exists? :" + file.exists());
            } else {
                System.out.println("File already exists!");
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }

        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String[] input = s.nextLine().split("\\|");
            if (input[1].strip().equals("Task Type") || input[1].strip().equals("----------")) {
                continue;
            }

            String type = input[1].strip();
            boolean isCompleted = Boolean.parseBoolean(input[2].strip());
            String description = input[3].strip();
            String time = input[4].strip();
            switch (type) {
                case EVENT:
                    int toIndex = time.indexOf("to");
                    String beforeTo = time.substring(0, toIndex);
                    String afterTo = time.substring(toIndex);
                    String amendedTime = beforeTo + '/' + afterTo;
                    tasklist.addToList(description + " /from " + amendedTime, TaskType.EVENT, isCompleted);
                    break;
                case DEADLINE:
                    tasklist.addToList(description + " /by " + time, TaskType.DEADLINE, isCompleted);
                    break;
                case TODO:
                    tasklist.addToList(description, TaskType.TODO, isCompleted);
                    break;
                default:
                    throw new dataCorruptedException();
            }
        }
    }

    /**
     * Adds file header for the markdown file
     * @throws IOException if something else is wrong
     */
    public void addHeader() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(FILE_HEADER);
        fw.close();
    }

    /**
     * Updates entire file with list of tasks
     * @param tasklist current list of tasks
     * @throws IOException if something else is wrong
     */
    public void updateFile(TaskList tasklist) throws IOException {
        addHeader();
        for (Tasks task : tasklist.getTaskList()) {
            appendTaskToFile(task);
        }
    }

    /**
     * Write new tasks to the file
     * @param task task to be written
     * @throws IOException if something else is wrong
     */
    public void appendTaskToFile(Tasks task) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write(toMarkdown(task));
        fw.close();
    }

    /**
     * Converts task to format in markdown file
     * @param task task to be converted
     * @return task in appropriate format for file
     */
    private String toMarkdown(Tasks task) {
        String type = task.getClass().getSimpleName();
        boolean taskIsDone = task.getIsDone();
        String description = task.getTaskDescription();
        String time = "";
        if (!type.equals("Todo")) {
            time = task.getTime();
        }
        return "| " + type + " | " + taskIsDone + " | " + description + " | " + time + " |\n";
    }
}
