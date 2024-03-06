package timl.utility;
import timl.exceptions.TimException;
import timl.task.Task;
import timl.task.TaskManager;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Scanner;

/**
 * This class provides functionalities for managing files related to task data, including exporting data to a file and
 * importing data from a file.
 */
public class FileManager {
    protected static final String DATA_PATH = "./data/";
    protected static final String FILE_NAME= "tasklist.txt";

    /**
     * Check for the existence of a data file, reads its contents and adds tasks through the task manager
     *
     * @param taskManager is an instance of TaskManager to which tasks will be added after reading from the file
     */
    public static void checkAndReadFile(TaskManager taskManager){
        File directory = new File(DATA_PATH);

        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File saveFile = new File(DATA_PATH + FILE_NAME);
            if (!saveFile.exists()) {
                return;
            }

            Scanner s = new Scanner(saveFile);
            String data;

            while (s.hasNext()) {
                data = s.nextLine();
                Task newTask = FileParser.readTask(data);
                taskManager.addTask(newTask);
            }
            s.close();
        } catch (IOException e) {
            Printer.printIOException();
        } catch (TimException e) {
            Printer.printEmptyTodoCommand();
        }
    }

    /**
     * Exports data from the task manager to a file located at the specified data path
     *
     * @throws IOException if an I/O error happens during file creation of writing
     */
    public static void exportData() throws IOException {
        File saveFile = new File(DATA_PATH + FILE_NAME);
        if (!saveFile.createNewFile()) {
                saveFile.delete();
                saveFile.createNewFile();
            }

        FileWriter fw = new FileWriter(saveFile);
        BufferedWriter writer = new BufferedWriter(fw);
        for (Task t : TaskManager.list) {
            String data = null;
            switch (TaskManager.getTaskType(t)) {
                case "[T]":
                    data = FileParser.convertTodoToFile(t.getStatus());
                    break;
                case "[D]":
                    data = FileParser.convertDeadlineToFile(t.getStatus());
                    break;
                case "[E]":
                    data = FileParser.convertEventToFile(t.getStatus());
                    break;
            }

            if (data != null) {
                writer.write(data + "\n");
            }
        }

        writer.close();
        fw.close();
    }
}

