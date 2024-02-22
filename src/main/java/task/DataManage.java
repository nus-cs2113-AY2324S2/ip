package task;

import java.io.*;

import exceptions.InputException;

public class DataManage {
    private static final String FILE_PATH = "data/chris.dat";
    private static final String FOLDER_PATH = "data";
    private static final String TXT_PATH = "data/chris.txt";

    public static void createFolder() {
        File parentDirectory = new File(FOLDER_PATH);
        File newFile = new File(FILE_PATH);
        if (!parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }
    }

    public static void createText() {
        File textFile = new File(TXT_PATH);
        try {
            if (!textFile.createNewFile()) {
                System.out.println("already have an existing file locally");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveText(TaskLists data) {
        try (FileWriter fw = new FileWriter(TXT_PATH, false)) {
            fw.write(data.print());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveData(TaskLists data) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            output.writeObject(data);
        } catch (FileNotFoundException fileError) {
            System.out.println(fileError.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static TaskLists readSavedData() throws InputException {
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (TaskLists) reader.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new InputException("no file error + create file error");
        }
    }
}
