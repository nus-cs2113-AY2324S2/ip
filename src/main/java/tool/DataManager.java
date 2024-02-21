package tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import exception.InputException;
import task.TaskList;

public class DataManager {
    private static final String FILE_PATH = "data/zukeBot.dat";
    private static final String FOLDER_PATH = "data";

    public static void createFolder() {
        File newFile = new File(FILE_PATH);
        File parentDir = new File(FOLDER_PATH);
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    public static void saveData(TaskList data) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            output.writeObject(data);
        } catch (FileNotFoundException noFileError) {
            ResponseManager.indentPrint(ResponseManager.NO_FILE_ERROR);
        } catch (IOException IOError) {
            ResponseManager.indentPrint(IOError.getMessage() + "\n" +
                    ResponseManager.SAVE_FILE_ERROR);
        }
    }

    public static TaskList readSavedData() throws InputException {
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (TaskList) reader.readObject();
        } catch (ClassNotFoundException | IOException Error) {
            throw new InputException(ResponseManager.NO_FILE_ERROR +
                    ResponseManager.CREATE_FILE_MESSAGE);
        }
    }

}
