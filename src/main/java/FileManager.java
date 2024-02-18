import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import exception.AdamException;
import task.TaskList;
import ui.Message;

public class FileManager {
    private static final String TASKS_FILE_PATH = "tasks.dat";

    public static void saveTasks(TaskList tasks) throws AdamException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(TASKS_FILE_PATH))) {
            outputStream.writeObject(tasks);
        } catch (IOException error) {
            throw new AdamException(Message.FILE_SAVE_ERROR_MESSAGE);
        }
    }

    public static TaskList loadTasks() throws AdamException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(TASKS_FILE_PATH))) {
            return (TaskList) inputStream.readObject();
        } catch (IOException | ClassNotFoundException error) {
            throw new AdamException(Message.FILE_LOAD_ERROR_MESSAGE);
        }
    }
}
