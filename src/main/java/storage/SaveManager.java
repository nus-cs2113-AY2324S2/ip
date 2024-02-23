package storage;

import logic.LogicManager;
import util.HorizontalGenerator;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class SaveManager {
    private static String tasksFilePath = "data/tasks.txt";
    private final File tasksFile = new File(SaveManager.tasksFilePath);

    private static void printDataNotSavedWarning() {
        System.out.println("Data in this session will not be recorded");
    }

    private void createDataFiles() {
        if (!tasksFile.getParentFile().exists() && !tasksFile.getParentFile().mkdirs()) {
            SaveManager.printDataNotSavedWarning();
            return;
        }
        try {
            tasksFile.createNewFile();
        } catch (IOException e) {
            SaveManager.printDataNotSavedWarning();
        }
    }

    /**
     * Loads existing data from the data files.
     * If the data files do not exist, they are created.
     * @param logicManager the LogicManager object to process the loaded data
     */
    public void loadData(LogicManager logicManager) {
        try {
            HorizontalGenerator.printHorizontal();
            System.out.println("loading existing data");
            HorizontalGenerator.printHorizontal();
            Scanner scanner = new Scanner(tasksFile);
            while (scanner.hasNext()) {
                logicManager.processCommand(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            HorizontalGenerator.printHorizontal();
            System.out.println("No existing data...");
            HorizontalGenerator.printHorizontal();
            createDataFiles();
        }
    }

    /**
     * Clears the data file.
     * @throws IOException if an error occurs when clearing the file
     */
    public void clearFile() throws IOException{
        FileWriter fileWriter = new FileWriter(tasksFile);
        fileWriter.write("");
        fileWriter.close();
    }

    /**
     * Writes the data to the data file.
     * Adds a new line after the data.
     * @param data the data to be written to the file
     * @throws IOException if an error occurs when writing to the file
     */
    public void writeToFile(String data) throws IOException{
        FileWriter fileWriter = new FileWriter(tasksFile, true);
        fileWriter.write(data);
        fileWriter.write(System.lineSeparator());
        fileWriter.close();
    }
}
