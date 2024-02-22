package timl.utility;
import timl.exceptions.TimException;
import timl.task.Task;
import timl.task.TaskManager;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Scanner;
public class FileManager {
    protected static final String DATA_PATH = "./data/";
    protected static final String FILE_NAME= "tasklist.txt";
    public static void checkAndReadFile(TaskManager taskManager, Task[] list ){
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
                taskManager.addTask(newTask, list);
            }
            s.close();
        } catch (IOException e) {
            Printer.printIOException();
        } catch (TimException e) {
            Printer.printEmptyContent();
        }
    }
    public static void exportData(Task[] tasks) throws IOException {
        File saveFile = new File(DATA_PATH + FILE_NAME);
        if (!saveFile.createNewFile()) {
                saveFile.delete();
                saveFile.createNewFile();
            }


        FileWriter fw = new FileWriter(saveFile);
        BufferedWriter writer = new BufferedWriter(fw);
        for (Task t : tasks) {
            String data = null;
            switch (TaskManager.getTaskType(t)) {
                case "[T]":
                    data = FileParser.getTodoData(t.getStatus());
                    break;
                case "[D]":
                    data = FileParser.getDeadlineData(t.getStatus());
                    break;
                case "[E]":
                    data = FileParser.getEventData(t.getStatus());
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

