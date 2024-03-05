package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Task;
import parsers.Parser;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Parser.parseTask(line);
                if (task != null) {
                    loadedTasks.add(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("OOPS! No saved tasks found, starting with an empty list :>");
            new File(file.getParent()).mkdirs();
        }

        return loadedTasks;
    }

    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();

            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            FileWriter writer = new FileWriter(file);
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("OOPS! An error occurred while saving tasks :<");
            e.printStackTrace();
        }
    }

}
