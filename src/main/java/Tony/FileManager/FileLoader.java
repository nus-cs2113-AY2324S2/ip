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

    public FileLoader(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
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
