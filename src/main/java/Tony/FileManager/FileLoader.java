package Tony.FileManager;

import Tony.task.Todo;
import Tony.task.Deadline;
import Tony.task.Event;
import Tony.Tony;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileLoader {
    protected static final String DATA_PATH = "./data/tonytask.txt";
    protected static final String SEPARATOR = " \\| ";
    public static void checkFileExists() throws IOException {
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

    private static void loadDataFromFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int lineCount = 0;
        while (scanner.hasNextLine()) {
            String lineFromFile = scanner.nextLine();
            String[] taskSplit = lineFromFile.split(SEPARATOR);
            String taskSymbol = taskSplit[0].trim();
            boolean isDone = taskSplit[1].trim().equals("1");
            switch (taskSymbol) {
            case "T":
                Tony.tasks.add(new Todo(taskSplit[2].trim()));
                break;
            case "D":
                Tony.tasks.add(new Deadline(taskSplit[2].trim(), taskSplit[3].trim()));
                break;
            case "E":
                String[] fromAndTo = taskSplit[3].split("to");
                Tony.tasks.add(new Event(taskSplit[2].trim(), fromAndTo[0].trim(), fromAndTo[1].trim()));
                break;
            }
            if (isDone) {
                Tony.tasks.get(lineCount).markDone();
            } else {
                Tony.tasks.get(lineCount).markNotDone();
            }
            lineCount++;
        }
    }
}
