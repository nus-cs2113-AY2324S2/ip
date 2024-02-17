package bossman;

import bossman.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataStorage {
    public final TaskList TASK_LIST;
    private static final String FILE_PATH = "data/BossMan.txt";

    public DataStorage() throws IOException {
        this.TASK_LIST = new TaskList();
        loadTasksFromFile();
    }

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
                    String by = formattedData[3];
                    Task deadlineTask = new Deadline(description, isMark, by);
                    TASK_LIST.addTask(deadlineTask);
                    break;
                case "E":
                    String from = formattedData[3];
                    String to = formattedData[4];
                    Task eventTask = new Event(description, isMark, from, to);
                    TASK_LIST.addTask(eventTask);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            createFolder();
        }
    }

    public static void createFolder() throws IOException {
        File f = new File(FILE_PATH);
        f.getParentFile().mkdirs();
        f.createNewFile();
    }

    protected void saveTasksToFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task currTask : TASK_LIST.getTasks()) {
            fw.write(currTask.formatForSave() + System.lineSeparator());
        }
        fw.close();
    }

    private String[] parseDataCsvFormat(String csvInput) {
        return csvInput.trim().split(",", 5);
    }
}
