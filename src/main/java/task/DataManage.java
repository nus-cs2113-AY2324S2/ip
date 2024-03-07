package task;

import java.io.*;

import exceptions.InputException;

public class DataManage {
    private static final String FILE_PATH = "data/chris.dat";
    private static final String FOLDER_PATH = "data";
    private static final String TXT_PATH = "data/chris.txt";

    /**
     * Creates a folder to store the data if it does not exist.
     */
    public static void createFolder() {
        File parentDirectory = new File(FOLDER_PATH);
        File newFile = new File(FILE_PATH);
        if (!parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }
    }

    /**
     * Creates a file to store the data if it does not exist.
     */
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

    /**
     * Saves the data to a text file.
     *
     * @param data List of tasks stored inside the ChatBot.
     */
    public static void saveText(TaskLists data) {
        try (FileWriter fw = new FileWriter(TXT_PATH, false)) {
            fw.write(data.print());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves the data to a binary file.
     *
     * @param data List of tasks stored inside the ChatBot.
     */
    public static void saveData(TaskLists data) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            output.writeObject(data);
        } catch (FileNotFoundException fileError) {
            System.out.println(fileError.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads the data from a binary file.
     *
     * @return List of tasks stored inside the ChatBot.
     * @throws InputException If the file is not found or there is an I/O error.
     */
    public static TaskLists readSavedData() throws InputException {
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (TaskLists) reader.readObject();
        } catch (ClassNotFoundException e) {
            throw new InputException("class not found");
        } catch (IOException inputErr) {
            throw new InputException("I/O error");
        }
    }
}
