package Tony.FileManager;

import Tony.task.Task;
import Tony.utility.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileLoader {
    protected static final String DATA_PATH = "./data/tonytask.txt";
    private ArrayList<Task> tasks;

    /**
     * Represents a <code>FileLoader</code> object that loads the tonytask.txt file if available.
     * Or create a new file if not found.
     * @param tasks is the current list of tasks to save from the file.
     */
    public FileLoader(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Check if a directory and file exists. Else, program will create one.
     * @throws IOException if there is error in locating the file in specified path.
     */
    public void checkFileExists() throws IOException {
        File directory = new File("./data/");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(DATA_PATH);
        if (!file.exists()) {
            System.out.println("File not found. File will be created!");
            file.createNewFile();
        }
        loadDataFromFile(file);
    }

    /**
     * Load data from file line by line.
     * @param file File found in path
     * @throws FileNotFoundException If there is error locating file.
     */

    private void loadDataFromFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int lineCount = 0;
        while (scanner.hasNextLine()) {
            Parser parser = new Parser(tasks);
            parser.extractLineFromFile(scanner, lineCount);
            lineCount++;
        }
    }


}
